Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.util.*',
    'Ext.toolbar.Paging',
    'Ext.ModelManager',
    'Ext.tip.QuickTipManager'
]);


var sendMail = function(documentId,documentDate)
{
	Ext.Ajax.request({
	    url: 'senddocumentbymail',
	    params: {
	        documentId: documentId,
	        documentDate:documentDate
	    },
	    success: function(response){
	        var text = response.responseText;
	        return true;
	    },
		   failure: function(response, opts) {
			   Ext.Msg.alert('Status', 'Mail Failed.');
			   return false;
		   }
	});
	//return false;
};

var deleteDocument = function(documentId,documentDate)
{
	Ext.Ajax.request({
	    url: 'deletedocument',
	    params: {
	        documentId: documentId,
	        documentDate:documentDate
	    },
	    success: function(response){
	        var text = response.responseText;
	        return true;
	    },
		   failure: function(response, opts) {
			   Ext.Msg.alert('Status', 'Delete failed.');
			   return false;
		   }
	});
	//return false;
};

Ext.onReady(function(){
   Ext.tip.QuickTipManager.init();
   
       Ext.define('documentModel', {
        extend: 'Ext.data.Model',
		 fields: [ 'documentId', 'documentName',{name: 'documentDate', mapping: 'documentDate', type: 'date', dateFormat: 'timestamp'},'edate'],
        idProperty: 'documentId'
       
    });
	
	 var store = Ext.create('Ext.data.Store', {
        pageSize: 10,
        model: 'documentModel',
        remoteSort: true,
		$color:'#fffff',
        proxy: {
            // load using script tags for cross domain, if the data in on the same domain as
            // this page, an HttpProxy would be better
            type: 'ajax',
            //url: 'http://www.sencha.com/forum/topics-browse-remote.php',
			url:'getdocumentlist',
            reader: {
				type: 'json',
                root: 'data',
                totalProperty: 'totalCount'
            },
            // sends single sort as multi parameter
            simpleSortMode: true
        },
        sorters: [{
            property: 'edate',
            direction: 'DESC'
        }]
    });

		function renderDoc(value, p, record) {
        return Ext.String.format(
            '<b><a href="./get_document_in_pdf?documentId={1}&time={2}" target="_blank">{0}</a>',
            value,
            record.data.documentId,
            record.data.edate
        );
    }
    
    function renderDate(value, p, r) {
        return Ext.String.format('{0}', Ext.Date.dateFormat(value, 'M j, Y, g:i a'));
    }
	
	var grid = Ext.create('Ext.grid.Panel', {
        width: 600,
       height: 600,
        title: 'Document Panel',
        store: store,
		frame: true,
        //disableSelection: true,
        //loadMask: true,
        // grid columns
        columns:[
		{xtype: 'rownumberer',
			text:'No.',
			width:'3%'},
		{
            text: "Document Name",
            dataIndex: 'documentName',
            width: '15%',
            align: 'left',
            sortable: false
        },
        {
            text: "Date",
            dataIndex: 'documentDate',
            align: 'left',
            width: '18%',
            sortable: false
        },{
            xtype:'actioncolumn',
            width:'20%',
			text:'option',
            items: [{
                icon: '../resources/icon/correct.png',  // Use a URL in the icon config
                tooltip: 'Activate',
                handler: function(grid, rowIndex, colIndex) {
                    var rec = grid.getStore().getAt(rowIndex);
                    activation('activateuser',rec.get('user_id'));
                    rec.set('active',true);
                    store.load();
                  
                }
            },{
                icon: '../resources/icon/remove.png',
                tooltip: 'Deactivate',
                handler: function(grid, rowIndex, colIndex) {
                	 var rec = grid.getStore().getAt(rowIndex);
                    activation('deactivateuser',rec.get('user_id'));
                    rec.set('active',false);
                    store.load();
                }
            }]
        }],
		  bbar: Ext.create('Ext.PagingToolbar', {
            store: store,
            displayInfo: true,
            emptyMsg: "No User registered"
        }),
        renderTo: 'form-ct'
    });
	
	 store.loadPage(1);
});