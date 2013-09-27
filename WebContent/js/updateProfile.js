
Ext.require([
    'Ext.form.*'
]);
// hbox container for postal code and phone number
var postalCode_phone = Ext.create('Ext.container.Container', {
    layout: {
        type: 'hbox'
    },
    defaults: {
        xtype: 'textfield',
        flex: 1,
        style: {
            padding: '5px',
			
        }
    },
    items: [{
        fieldLabel: 'Postal Code',
        name: 'postal_code',
		regex: /^\d{6}$/i,
		regexText: "Please enter valid postal code",
        allowBlank: false,
		blankText: 'Enter your postal code',
		emptyText: 'Enter Postal code',
		 anchor: '40%',
		margin: '10 5 10 0'
    },{
         fieldLabel: 'Mobile number',
        name: 'phone_number',
		regex: /^\d{10}$/i,
		regexText: "Please enter valid mobile number without (+91)",
        allowBlank: false,
		blankText: 'Enter your mobile number',
		emptyText: 'Enter your Mobile number',
		margin: '10 0 10 0'
    }]
});

// hbox container for buttons update and cancel
var buttons = Ext.create('Ext.container.Container', {
    layout: {
        type: 'hbox'
    },
    defaults: {
        xtype: 'textfield',
        flex: 1,
        style: {
            padding: '5px',
			
        }
    },
    items: [{
        xtype: 'button',
		text: 'Update',
		formBind: false,
        disabled: false,
		width: 100,
		margin: '10 5 0 0',
        handler: function() {
            var form = this.up('form').getForm();
            if (form.isValid()) {
                form.submit({
                    success: function(form, action) {
                       Ext.Msg.alert('Success', "Successfully updated profile");
                    },
                    failure: function(form, action) {
                        Ext.Msg.alert('Failed', action.result.msg);
                    },
                });
            }
        }
    },{
         xtype: 'button',
		text: 'Cancel',
		margin: '10 0 0 0',
		handler: function(){
		history.back();
		}
		
    }
	]
});

//Data Store for States
var states = Ext.create('Ext.data.Store', {
    fields: ['name'],
    data : [
        {"name":"Maharashtra"},
        {"name":"Gujrat"},
        {"name":"Karnataka"},
		{"name":"Madhyapradesh"}
    ]
});

//StateCombobox
var statecombobox = Ext.create('Ext.form.ComboBox', {
    fieldLabel: 'Choose State',
    store: states,
    queryMode: 'local',
    displayField: 'name',
    valueField: 'name',
    name: 'state',
	allowBlank: false,
	blankText: 'Enter your State',
	typeAhead: true,
	emptyText:'Select a State...'
});

// hbox container for firstName and lastName
var names = Ext.create('Ext.container.Container', {
    layout: {
        type: 'hbox'
    },
    defaults: {
        // implicitly create Container by specifying xtype
        xtype: 'textfield',
        flex: 1,
        style: {
            padding: '5px',
			
        }
    },
    items: [{
        fieldLabel: 'First Name',
        name: 'first_name',
		vtype: 'alpha',
		vtypeText: 'Please enter valid name',
        allowBlank: false,
		blankText: 'Enter your first name',
		emptyText: 'First Name',
		margin: '10 5 10 0'
    },{
         fieldLabel: 'Last Name',
        name: 'last_name',
		vtype: 'alpha',
		vtypeText: 'Please enter valid name',
        allowBlank: false,
		blankText: 'Enter your last name',
		emptyText: 'Last Name',
		margin: '10 0 10 0'
    }]
});

// hbox container for state and city
var stateandcity = Ext.create('Ext.container.Container', {
    layout: {
        type: 'hbox'
    },
    defaults: {
        // implicitly create Container by specifying xtype
        flex: 1,
        style: {
            padding: '5px',
			
        }
    },
    items: [{
		  xtype: 'textfield',
        fieldLabel: 'City',
        name: 'city',
		vtype: 'alpha',
		vtypeText: 'Please enter valid city',
        allowBlank: false,
		blankText: 'Enter your city',
		emptyText: 'Enter your City',
		margin: '0 5 0 0',
		anchor: '40%'
    },{
	  xtype: 'combobox',
	fieldLabel: 'Choose State',
	name: 'state',
    store: states,
    queryMode: 'local',
    displayField: 'name',
    valueField: 'name',
	allowBlank: false,
	blankText: 'Enter your State',
	typeAhead: true,
	emptyText:'Select a State...'
	}]
});




Ext.onReady(function() {

    var formPanel = Ext.create('Ext.form.Panel', {
        frame: true,
        title: 'Update Profile',
        width: 340,
        bodyPadding: 5,
        url : 'updateuserprofile',
		defaultType: 'textfield',
        fieldDefaults: {
            labelAlign: 'top',
            labelWidth: 90,
            anchor: '100%',
			msgTarget: 'side'
        },

        items: [
		names,
		{
        fieldLabel: 'Street Address',
        name: 'street_address',
        allowBlank: false,
		blankText: 'Enter your Street address',
		emptyText: 'Enter your Street Address'
		}
		,stateandcity,
		,postalCode_phone
		,buttons
		]
		});

    formPanel.render('form-ct');
	//Loading form
	formPanel.getForm().load({
    url: 'getuserprofile',
    success:function(form,action)
    {
    	var responseObj = Ext.JSON.decode(action.response.responseText);
//    	Ext.Msg.alert("response",action.response.responseText);
    },
    failure: function(form, action) {
        Ext.Msg.alert("Load failed", action.response.responseText);
    }
});

});
