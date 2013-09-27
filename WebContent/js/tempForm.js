Ext
		.onReady(function() {
			Ext.QuickTips.init();

			// Create a variable to hold our EXT Form Panel.
			// Assign various config options as seen.
			var login = new Ext.FormPanel(
					{
						// labelWidth:1,
						width : 400,
						url : 'StorageService',
						frame : true,
						bodyPadding : '5 5 0',
						title : 'Question Answer',
						defaultType : 'textfield',
						renderTo : 'form-ct',
						monitorValid : true,
						layout : 'form',
						items : [ {
							xtype : 'textfield',
							fieldLabel : 'New Surname',
							name : 'nsname',
						// allowBlank:true
						}, {
							xtype : 'textfield',
							fieldLabel : 'New Firstname',
							name : 'nfname',
						// allowBlank:true
						}, {
							xtype : 'textfield',
							fieldLabel : 'Old Surname',
							name : 'osname',
						// allowBlank:false
						}, {
							xtype : 'textfield',
							fieldLabel : 'Old Firstname',
							name : 'ofname',
						// allowBlank:false
						}, {
							xtype : 'textfield',
							fieldLabel : 'Profession',
							name : 'proff',
						// allowBlank:false
						}, {
							xtype : 'textareafield',
							fieldLabel : 'Address',
							name : 'address',
						// allowBlank:false
						}, {
							xtype : 'datefield',
							fieldLabel : 'Date for taking new name',
							name : 'date1',
						// allowBlank:false
						}, {
							xtype : 'textfield',
							fieldLabel : 'Name of Second Witness',
							name : 'fiwina',
						// allowBlank:false
						}, {
							xtype : 'textareafield',
							fieldLabel : 'Address of 1st Witness',
							name : 'adfiwit',
						// allowBlank:false
						}, {
							xtype : 'textfield',
							fieldLabel : 'Name of 2nd Witness',
							name : 'nasewi',
						// allowBlank:false
						}, {
							xtype : 'textareafield',
							fieldLabel : 'Address of 2nd Witness',
							name : 'adsewit',
						// allowBlank:false
						} ],

						// All the magic happens after the user clicks the
						// button
						buttons : [ {
							text : 'Submit',
							formBind : false,
							// Function that fires when user clicks the button
							handler : function() {
								login
										.getForm()
										.submit(
												{
													method : 'POST',
													waitTitle : 'Connecting',
													waitMsg : 'Sending data...',


													success : function() {
														Ext.Msg.alert('Success','Document Generated successfully');
														window.location = './viewdocument.html';
													},

													failure : function(form,action) {
														Ext.Msg.alert('Failure','Document generation failed');
//														login.getForm().reset();
													}
												});
							}
						} ]
					});
		});