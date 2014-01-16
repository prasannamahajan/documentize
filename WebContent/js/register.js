
Ext.require([
    'Ext.form.*'
]);
// hboc container for password and confirm password
var passwords = Ext.create('Ext.container.Container', {
    layout: {
        type: 'hbox'
    },
    defaults: {
        xtype: 'textfield',
        flex: 1,
        style: {
            margin: '5px',
			
        }
    },
    items: [{
        fieldLabel: 'Password',
        name: 'pwd',
		inputType: 'password',
        allowBlank: false,
		minLength: 6,
		blankText: 'Enter Password',
		emptyText: 'Enter your Password',
		margin: '0 5 0 0'
	},{
        fieldLabel: 'Confirmed Password',
        name: 'cpassword',
		inputType: 'password',
        allowBlank: false,
		blankText: 'Enter your password again',
		emptyText: 'Re-Enter your Password',
		margin: '0 5 0 0',
		validator: function(value) {
		var password = this.previousSibling('[name=pwd]');
		return (value === password.getValue()) ? true : 'Passwords do not match.';
		}
	}]
});
// hbox container for postal code and phone number
var postalCode_phone = Ext.create('Ext.container.Container', {
    layout: {
        type: 'hbox'
    },
    defaults: {
        xtype: 'textfield',
        flex: 1
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
		margin: '0 0 0 0'
    },{
         fieldLabel: 'Mobile number',
        name: 'phone_number',
		regex: /^\d{10}$/i,
		regexText: "Please enter valid mobile number without (+91)",
        allowBlank: false,
		blankText: 'Enter your mobile number',
		emptyText: 'Enter your Mobile number',
		margin: '0 0 0 0'
    }]
});

// hbox container for buttons register
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
		text: 'Register',
		scale: 'small',
		formBind: false, //only enabled once the form is valid
        disabled: false,
		anchor: '100%',
		margin: '10 0 0 0',
        handler: function() {
            var form = this.up('form').getForm();
			var passwd= form.findField("pwd").getValue();
			//----------------------
//			var checkVal = form.findField("state").getValue();
//			Ext.Msg.alert("State",checkVal);
			//-----------------------
			passwd = CryptoJS.MD5(passwd).toString();
            if (form.isValid()) {
                form.submit({
					waitMsg: 'Connecting..',
					params:{
					password:passwd
					},
                    success: function(form, action) {
                       window.location = "./response.jsp?id=1";
                    },
                    failure: function(form, action) {
                    	window.location = "./response.jsp?id=4";
                    }
                });
            }
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
		{"name":"Madhyapradesh"},
        {"name":"Andrapradesh"},
        {"name":"Punjab"}
    ]
});

//StateCombobox
var statecombobox = Ext.create('Ext.form.ComboBox', {
    fieldLabel: 'Choose State',
    store: states,
    queryMode: 'local',
    displayField: 'name',
    valueField: 'name',
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
    store: states,
    queryMode: 'local',
    displayField: 'name',
    name: 'state',
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
        title: 'Register',
        width: 350,
        bodyPadding: 5,
		//edit color of background of form
		bodyStyle: 'background-color: #fffff',
		defaultType: 'textfield',
		url: 'ajaxregister',
        fieldDefaults: {
			msgTarget: 'side',
            labelAlign: 'top',
            labelWidth: 90,
            anchor: '100%'
        },

        items: [
		names,
		{
        fieldLabel: 'Email',
        name: 'email',
		vtype: 'email',
		vtypeText: 'Please enter valid email',
        allowBlank: false,
		blankText: 'Enter your email',
		emptyText: 'Enter your email'
		},	
		passwords,
		{
        fieldLabel: 'Street Address',
        name: 'street_address',
        allowBlank: false,
		blankText: 'Enter your Street address',
		emptyText: 'Enter your Street Address'
		}
		//Ladling container of state and city
		,stateandcity,
		//Loading container of postal code and phone number
		,postalCode_phone
		//Loading container of buttons
		,buttons
		]
		});

    formPanel.render('form-ct');

});
