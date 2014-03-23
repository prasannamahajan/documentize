Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.util.*',
    'Ext.toolbar.Paging',
    'Ext.ModelManager',
    'Ext.tip.QuickTipManager'
]);

Ext.onReady(function(){
   Ext.tip.QuickTipManager.init();
   
       Ext.define('documentModel', {
        extend: 'Ext.data.Model',
        fields: [
            'documentId', 'documentName',{name: 'documentDate', mapping: 'documentDate', type: 'date', dateFormat: 'timestamp'}
        ],
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
            property: 'documentId',
            direction: 'DESC'
        }]
    });
	
	function renderDoc(value, p, record) {
        return Ext.String.format(
            '<b><a href="./get_document_in_pdf?documentId={1}&epochTime={2}" target="_blank">{0}</a>',
            value,
            record.data.documentId,
            record.data.documentDate
        );
    }
    
    function renderDate(value, p, r) {
        return Ext.String.format('{0}', Ext.Date.dateFormat(value, 'M j, Y, g:i a'));
    }
	
	var grid = Ext.create('Ext.grid.Panel', {
        width: 600,
      //  height: 500,
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
                    Ext.Msg.alert('Mail','You will recieve mail shortly');
                }
            }]
        },
        {
            xtype:'actioncolumn',
            width:'5%',
			//text:'Delete',
            items: [{
                icon: '../resources/icon/remove.png',
                tooltip: 'Delete',
                handler: function(grid, rowIndex, colIndex) {
                	Ext.Msg.alert('Delete file','File deleted');
                }
            }]
        }],
		  bbar: Ext.create('Ext.PagingToolbar', {
            store: store,
            displayInfo: true,
            emptyMsg: "No document created "
        }),
        renderTo: 'document-ct'
    });
	
	 store.loadPage(1);
});