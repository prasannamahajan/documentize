
Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.create('Ext.form.Panel', {
		bodyPadding : '5 5 0',
		labelAlign : 'top',
		width : 500,
		title : 'Add Lawyer',
		frame : true,
		bodyPadding : 10,
		bodyBorder : true,
		url : './insertlawyer',
		layout : 'form',
		fieldDefaults : {
			labelAlign : 'top',
			msgTarget : 'side',
			labelWidth : 75
		},
		defaults : {
			anchor : '80%'
		},

		// The fields
		defaultType : 'textfield',
		items : [ {
			fieldLabel : 'Full Name',
			name : 'fullname',
			allowBlank : false,
			inputWidth: 300,
			//height:50,
			blankText : 'Enter your full name',
			emptyText : 'Sirname your-name father-name'
		},
		{
			fieldLabel : 'Specialization',
			name : 'specialization',
			allowBlank : false,
			inputWidth: 300,
			//height:50,
			blankText : 'Your Specialization',
			emptyText : 'field seperated by ,'
		},
		 {
		 xtype : 'textareafield',
			fieldLabel : 'About Yourself',
			name : 'aboutself',
			allowBlank : false,
			inputWidth: 300,
			//height:50,
			blankText : 'Your Information',
			emptyText : 'Mr.x is very good..'
		},
		 {
			fieldLabel : 'Your Email',
			name : 'email',
			allowBlank : false,
			inputWidth: 300,
			//height:50,
			vtype: 'email',
			blankText : 'Enter your email id',
			emptyText : 'Email id'
		},
		{
			xtype : 'textareafield',
			fieldLabel : 'Address',
			name : 'address',
			allowBlank : true,
			inputWidth: 300,
			blankText : 'Enter your address',
			emptyText : 'Enter your address '
		},
		{
			fieldLabel : 'Your Mobile No',
			name : 'phonenumber',
			allowBlank : true,
			inputWidth: 300,
			//height:50,
			blankText : 'Enter your mobile number',
			emptyText : 'Phone Number'
		},
		{
			xtype : 'button',
			text : 'Add',
			formBind : false,
			disabled : false,
			//width : 100,
			margin: '10 0 0 0',
			handler : function() {
				var form = this.up('form').getForm();
				if (form.isValid()) {
					form.submit({
						method : 'POST',
						success : function(form, action) {
							Ext.Msg.alert('Success',"Lawyer added successfully");
							form.reset();
						},
						failure : function(form, action) {
							Ext.Msg.alert('Failed', "Sorry for inconvenience");
						},
						waitMsg : "Processing..."
					});
				}
			}
		}
		],
		renderTo : 'form-ct'
	});
});