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
	    }
	});
	return false;
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
	    }
	});
	return false;
};


Ext.onReady(function(){
   Ext.tip.QuickTipManager.init();
   
       Ext.define('documentModel', {
        extend: 'Ext.data.Model',
        idProperty: 'documentId',
        fields: [ 'documentId', 'documentName',{name: 'documentDate', mapping: 'documentDate', type: 'date', dateFormat: 'timestamp'},'edate']
       
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
            property: 'documentId',
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
        height: 500,
        title: 'Document Vault',
        store: store,
		frame: true,
        //disableSelection: true,
        //loadMask: true,
        // grid columns
        columns:[
		{xtype: 'rownumberer',
			text:'No.',
			width:'5%'},
		{
            text: "Document ",
            dataIndex: 'documentName',
            width: '40%',
            align: 'left',
            sortable: false,
			renderer: renderDoc
        },
        {
            text: "Date ",
            dataIndex: 'documentDate',
            width: '30%',
            align: 'left',
            sortable: false,
           renderer:renderDate
        },{
            xtype:'actioncolumn',
            width:'5%',
			//text:'Mail',
			align:'center',
            items: [{
                icon: '../resources/icon/mail.png',  // Use a URL in the icon config
                tooltip: 'Mail',
                handler: function(grid, rowIndex, colIndex) {
                	var rec = grid.getStore().getAt(rowIndex);
                	var result = sendMail(rec.get('documentId'),rec.get('edate'));
                	if(result==true)
                		Ext.Msg.alert('Mail','You will recieve mail shortly');
                	else
                		Ext.Msg.alert('Failed','Sorry for incovenience');
                }
            }]
        },
        {
            xtype:'actioncolumn',
            width:'5%',
            items: [{
                icon: '../resources/icon/edit.png',
                tooltip: 'Edit',
                handler: function(grid, rowIndex, colIndex) {
                	var rec = grid.getStore().getAt(rowIndex);
                	var recid = rec.get('documentId');
                	var recdate= rec.get('edate');
                	var recname= rec.get('documentName');
                	var location = "../user/updatedocument.html?documentId="+recid+"&documentDate="+recdate+"&documentName="+recname;
					window.open(location, '_blank');
                }
            }]
        },
        {
            xtype:'actioncolumn',
            width:'5%',
			
            items: [{
                icon: '../resources/icon/remove.png',
                tooltip: 'Delete',
                handler: function(grid, rowIndex, colIndex) {
                		var rec = grid.getStore().getAt(rowIndex);
                	var result = deleteDocument(rec.get('documentId'),rec.get('edate'));
                	if(result==true)
                		{
                		 grid.store.removeAt(rowIndex);
                		Ext.Msg.alert('Document','Document is deleted');
                		}
                	else
                		Ext.Msg.alert('Failed','Sorry for incovenience');
                }
            }]
        }],
		  bbar: Ext.create('Ext.PagingToolbar', {
            store: store,
            displayInfo: true,
            emptyMsg: "No document created "
        }),
        renderTo: 'form-ct'
    });
	
	 store.loadPage(1);
});