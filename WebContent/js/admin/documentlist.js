Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.util.*',
    'Ext.toolbar.Paging',
    'Ext.ModelManager',
    'Ext.tip.QuickTipManager'
]);

var deleteDocument = function(documentId,documentDate)
{
	Ext.Ajax.request({
	    url: '../admin/deletedocument',
	    params: {
	        documentId: documentId
	    },
	    success: function(response){
	        var text = response.responseText;
	        return true;
	    }
	});
};


Ext.onReady(function(){
   Ext.tip.QuickTipManager.init();
   
       Ext.define('documentModel', {
        extend: 'Ext.data.Model',
        idProperty: 'documentId',
        fields: [ 'documentId', 'documentName','documentCategory']
       
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
            '<b><a href="./editdocument.html?documentId={1}" target="_blank">{0}</a>',
            value,
            record.data.documentId
        );
    }
    
	
	var grid = Ext.create('Ext.grid.Panel', {
        width: 600,
        height: 500,
        title: 'Documents',
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
        },{
            text: "Id",
            dataIndex: 'documentId',
            width: '10%',
            align: 'left',
            sortable: false,
        },
        {
            text: "Category ",
            dataIndex: 'documentCategory',
            width: '30%',
            align: 'left',
            sortable: false,
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
            
                		 grid.store.removeAt(rowIndex);
                		Ext.Msg.alert('Document','Document is deleted');
                	
                }
            }]
        }],
		  bbar: Ext.create('Ext.PagingToolbar', {
            store: store,
            displayInfo: true,
            emptyMsg: "No document uploaded"
        }),
        renderTo: 'form-ct'
    });
	
	 store.loadPage(1);
});