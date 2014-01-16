
Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.create('Ext.form.Panel', {
		bodyPadding : '5 5 0',
		labelAlign : 'top',
		width : 500,
		title : 'Contact Us',
		frame : true,
		bodyPadding : 10,
		bodyBorder : true,
		url : 'contact-us',
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
			fieldLabel : 'Your Name',
			name : 'name',
			allowBlank : false,
			inputWidth: 300,
			//height:50,
			blankText : 'Enter your name',
			emptyText : 'Your Name'
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
			fieldLabel : 'Your Mobile No',
			name : 'mobile',
			allowBlank : true,
			inputWidth: 300,
			//height:50,
			blankText : 'Enter your mobile number',
			emptyText : 'Your Name'
		},
		{
			xtype : 'textareafield',
			fieldLabel : 'Message',
			name : 'message',
			allowBlank : true,
			grow : true ,
			height:200,
			blankText : 'Enter your message',
			emptyText : 'Enter your message '
		},{
			xtype : 'button',
			text : 'Send',
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
							Ext.Msg.alert('Success',"Message sent successfully");
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