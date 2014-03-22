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
   
       Ext.define('userModel', {
        extend: 'Ext.data.Model',
        fields: [
            'user_id', 'first_name','last_name','email','phone_number','role','street_address','city','state','postal_code','active'
        ],
        idProperty: 'documentId'
    });
	
	 var store = Ext.create('Ext.data.Store', {
        pageSize: 10,
        model: 'userModel',
        remoteSort: true,
		$color:'#fffff',
        proxy: {
            // load using script tags for cross domain, if the data in on the same domain as
            // this page, an HttpProxy would be better
            type: 'ajax',
            //url: 'http://www.sencha.com/forum/topics-browse-remote.php',
			url:'getuserslist',
            reader: {
				type: 'json',
                root: 'data',
                totalProperty: 'totalCount'
            },
            // sends single sort as multi parameter
            simpleSortMode: true
        },
        sorters: [{
            property: 'user_id',
            direction: 'DESC'
        }]
    });

	
	var grid = Ext.create('Ext.grid.Panel', {
        width: 1250,
      //  height: 500,
        title: 'User Information ',
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
            text: "User ID ",
            dataIndex: 'user_id',
            width: '5%',
            align: 'left',
            sortable: false
        },
        {
            text: "First Name",
            dataIndex: 'first_name',
            align: 'left',
            width: '8%',
            sortable: false
        },
        {
            text: "Last Name",
            dataIndex: 'last_name',
            align: 'left',
            width: '8%',
            sortable: false
        },
        {
            text: "Email",
            dataIndex: 'email',
            align: 'left',
            sortable: false,
            width: '20%'
        },
        {
            text: "Phone Number",
            dataIndex: 'phone_number',
            align: 'left',
            sortable: false,
            width: '10%'
        },
        {
            text: "Role",
            dataIndex: 'role',
            align: 'left',
            sortable: false,
            width: '5%'
        },
        {
            text: "Street Address",
            dataIndex: 'street_address',
            align: 'left',
            sortable: false,
            width: '10%'
        },
        {
            text: "City",
            dataIndex: 'city',
            align: 'left',
            sortable: false,
            width: '5%'
        },
        {
            text: "State",
            dataIndex: 'state',
            align: 'left',
            sortable: false,
            width: '8%'
        },
        {
            text: "Postal Code",
            dataIndex: 'postal_code',
            align: 'left',
            sortable: false,
            width: '8%'
        },
        {
            text: "Active",
            dataIndex: 'active',
            align: 'left',
            sortable: false,
            width: '5%',
        },{
            xtype:'actioncolumn',
            width:'20%',
			text:'option',
            items: [{
                icon: '../resources/icon/correct.png',  // Use a URL in the icon config
                tooltip: 'Activate',
                handler: function(grid, rowIndex, colIndex) {
                    Ext.Msg.alert('Activate','Account Activated');
                }
            },{
                icon: '../resources/icon/remove.png',
                tooltip: 'Deactivate',
                handler: function(grid, rowIndex, colIndex) {
                	Ext.Msg.alert('Deactivate Account','Account Deactivated');
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