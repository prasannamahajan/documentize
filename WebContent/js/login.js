// Button container for login and signup
var buttons = Ext.create('Ext.container.Container', {
	layout : {
		type : 'hbox'
	},
	defaults : {
		xtype : 'textfield',
		flex : 1,
		style : {
			padding : '5px',

		}
	},
	items : [
			{
				xtype : 'button',
				text : 'Login',
				formBind : false,
				disabled : false,
				width : 100,
				margin : '10 5 0 0',
				handler : function() {
					var form = this.up('form').getForm();
					var password = form.findField("pwd").getValue();
					password = CryptoJS.MD5(password).toString();
					if (form.isValid()) {
						Ext.Msg.alert('Success',
								"You clicked submit button and valid");
						form.submit({
							waitMsg: 'Connecting..',
							params : {
								password : password
							},
							success : function(form, action) {
								var responseObj = Ext.JSON.decode(action.response.responseText);
								window.location="./"+responseObj.role+"/home.html";
								//window.location = "./user/home.html";
							},
							failure : function(form, action) {
							//Ext.Msg.alert('Failed', action.result.msg);
								var errorDom = Ext.getDom('error');
								var message="username or password incorrect";
								var x=document.getElementById("error");
								x.innerHTML = message;
							},
							waitMsg : "Loading..."
						});
					}
				}
			}, {
				xtype : 'button',
				text : 'SignUp',
				margin : '10 0 0 0',
					handler : function() {
						window.location = "./register.html";
					}
			} ]
});

Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.create('Ext.form.Panel', {
		bodyPadding : '5 5 0',
		labelAlign : 'top',
		width : 250,
		title : 'login',
		frame : true,
		bodyPadding : 10,
		bodyBorder : true,
		url : 'ajaxlogin',
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
			fieldLabel : 'Email',
			name : 'email',
			vtype : 'email',
			vtypeText : 'Please enter valid email',
			allowBlank : false,
			blankText : 'Enter your email',
			emptyText : 'Enter your email'
		}, {
			fieldLabel : 'Password',
			name : 'pwd',
			inputType : 'password',
			allowBlank : false,
			minLength : 6,
			blankText : 'Enter Password',
			emptyText : 'Enter your Password'
		}, {
			xtype : 'checkboxfield',
			name : 'remember',
			value : 'true',
			fieldLabel : 'Keep me login',
			hideLabel : true,
			margin : '10 0 0 0',
			boxLabel : 'Keep me login'
		},
		// Adding Login and SignUp buttons
		buttons, {
			xtype : 'box',
			autoEl : {
				tag : 'a',
				href : 'forgetPassword.html',
				children : [ {
					tag : 'div',
					html : 'Forget Password'
				} ]
			},
			margin : '10 0 0 0',
			style : 'cursor:pointer;'
		} ],
		renderTo : 'form-ct'
	});
});