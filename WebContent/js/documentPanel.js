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
            'documentId', 'documentName'
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
            '<b><a href="Lawyer/user/getdocument?documentId={1}" target="_blank">{0}</a>',
            value,
            record.data.documentId
        );
    }
	
	var grid = Ext.create('Ext.grid.Panel', {
        width: 500,
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
			width:'10%'},
		{
            text: "Document ",
            dataIndex: 'documentName',
            width: '70%',
            align: 'left',
            sortable: false,
			renderer: renderDoc
        },{
            xtype:'actioncolumn',
            width:'20%',
			text:'option',
            items: [{
                icon: '../resources/icon/mail.png',  // Use a URL in the icon config
                tooltip: 'Mail',
                width:'10%',
                handler: function(grid, rowIndex, colIndex) {
                    Ext.Msg.alert('Mail','You will recieve mail shortly');
                }
            },{
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
            emptyMsg: "No topics to display"
        }),
        renderTo: 'form-ct'
    });
	
	 store.loadPage(1);
});