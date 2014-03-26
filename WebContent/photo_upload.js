var picBox = {
   columnWidth: '100 px',
   bodyStyle: 'padding:10px',
   items: [
      { xtype: 'box',
        autoEl: { tag: 'div',
           html: '<img id="pic" src='//dgdsbygo8mp3h.cloudfront.net/sites/default/files/blank.gif' data-original="' + Ext.BLANK_IMAGE_URL + '"            class="img-contact" />'
        }
      }
   ]
});

var picFiles = {
		   columnWidth: .65,
		   layout: 'form',
		   labelAlign:'top',
		   items: [{
		      xtype: 'textfield',
		      fieldLabel: 'Current',
		      labelSeparator: '',
		      name: 'currPic',
		      id:'currPic',
		      readOnly: true,
		      disabled:true,
		      anchor:'100%'
		    },
		    {
		      xtype: 'textfield',
		      fieldLabel: 'New (JPG or PNG only)',
		      labelSeparator: '',
		      name: 'newPic',
		      id:'newPic',
		      style:'width: 300px',
		      inputType: 'file',
		      allowBlank: false
		   }]
		}); 

function validateFileExtension(fileName) {
	   var exp = /^.*.(jpg|JPG|png|PNG)$/;
	   return exp.test(fileName);
	}

var pictUploadForm = new Ext.FormPanel({
	   frame: true,
	   title: 'Change Picture',
	   bodyStyle: 'padding:5px',
	   width: 420,
	   layout: 'column',
	   url: 'contact-picture.aspx',
	   method: 'POST',
	   fileUpload: true,
	   items: [picBox, picFiles],
	   buttons: [{
	      text: 'Upload Picture',
	      handler: function() {
	         var theForm = pictUploadForm.getForm();
	         if (!theForm.isValid()) {
	            Ext.MessageBox.alert('Change Picture',
	              'Please select a picture');
	            return;
	         }
	         if (!validateFileExtension(Ext.getDom('newPic').value)) {
	            Ext.MessageBox.alert('Change Picture',
	              'Only JPG or PNG, please.');
	            return;
	         }
	         theForm.submit({
	            params: { act: 'setPicture', id: 'contact1' },
	            waitMsg: 'Uploading picture'
	         })
	       }
	    },
	    {
	      text: 'Cancel'
	   }]
	});


pictUploadForm.on({
	   actioncomplete: function(form, action) {
	      if (action.type == 'load') {
	         var pic = action.result.data;
	         Ext.getDom('pic').src = pic.file;
	         Ext.getCmp('currPic').setValue(pic.file);
	      }
	      if (action.type == 'submit') {
	         var pic = action.result.data;
	         Ext.getDom('pic').src = pic.file;
	         Ext.getCmp('currPic').setValue(pic.file);
	         Ext.getDom('newPic').value = '';
	      }
	   }
	});

pictUploadForm.render(document.body);
pictUploadForm.getForm().load({ 
   params: { act: 'getPicture',
      id: 'contact1' 
   },
   waitMsg: 'Loading'
});

function validateFileExtension(fileName) {
	   var exp = /^.*.(jpg|JPG|png|PNG|txt|TXT)$/;
	   return (exp.test(fileName));
	});
	
	buttons: [{
		   text: 'Upload Picture',
		   handler: function() {
		      var theForm = pictUploadForm.getForm();
		      if (!theForm.isValid()) {
		         Ext.MessageBox.alert('Change Picture',
		           'Please select a picture');
		         return;
		      }
		      if (!validateFileExtension(Ext.getDom('newPic').value)) {
		         Ext.MessageBox.alert('Change Picture',
		           'Only JPG or PNG, please.');
		         return;
		      }
		      theForm.submit({
		         params: { act: 'setPicture', id: 'contact1'},
		         waitMsg: 'Uploading picture'
		      })
		   }
		});
		
		
		if (action.type == 'load') {
			   var pic = action.result.data;
			   Ext.getDom('pic').src = pic.file;
			   Ext.getCmp('currPic').setValue(pic.file);
			}
			if (action.type == 'submit') {
			   var pic = action.result.data;
			   Ext.getDom('pic').src = pic.file;
			   Ext.getCmp('currPic').setValue(pic.file);
			   Ext.getDom('newPic').value = '';
			});
			
			