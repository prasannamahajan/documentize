var documentName = "Name of the document";
var documentId = "documentid"; 
var jsonUrl = "1.json";
var active="card-";
document.title = "Title of document";
var jsonResponse="";
var displayResponse = function(){
 var win = Ext.widget('window', {
                                title: 'Terms of Use',
                                modal: true,
                                html: jsonResponse,           
                        });
                        win.show();
                        };

var Stringspace = function (len) {
	var t = [], i;
	for (i = 0; i < len; i++) {
		t.push(' ');
	}
	return t.join('');
};

var format = function (edit) {
				var text = edit.replace(/\n/g, ' ').replace(/\r/g, ' ');
				var t = [];
				var tab = 0;
				var inString = false;
				for (var i = 0, len = text.length; i < len; i++) {
					var c = text.charAt(i);
					if (inString && c === inString) {
						// TODO: \\"
						if (text.charAt(i - 1) !== '\\') {
							inString = false;
						}
					} else if (!inString && (c === '"' || c === "'")) {
						inString = c;
					} else if (!inString && (c === ' ' || c === "\t")) {
						c = '';
					} else if (!inString && c === ':') {
						c += ' ';
					} else if (!inString && c === ',') {
						c += "\n" + Stringspace(tab * 2);
					} else if (!inString && (c === '[' || c === '{')) {
						tab++;
						c += "\n" + Stringspace(tab * 2);
					} else if (!inString && (c === ']' || c === '}')) {
						tab--;
						c = "\n" + Stringspace(tab * 2) + c;
					}
					t.push(c);
				}
				return t.join('');
			};
			
var navigate = function (panel, direction) {
            var layout = panel.getLayout();
            layout[direction]();
            Ext.getCmp('move-prev').setDisabled(!layout.getPrev());
            Ext.getCmp('move-next').setDisabled(!layout.getNext());
            if(layout.activeItem.id==active)
            	Ext.getCmp('finish').setDisabled(false);
            else
            	Ext.getCmp('finish').setDisabled(true);
        }; 

 var disp = new Ext.form.Panel({
           bodyStyle: 'padding: 10px',
            layout:'card',
            title: "JSON viewer",
           // autoHeight: true,
           // frame:true,
            width:450,
            autoWidth: true,
            labelAlign: 'right',
            items: [
			{
            xtype:'textareafield',
            name: 'file',
            id:'file',
            allowBlank:false
        }]
});
var formPn = new Ext.form.Panel({
            bodyStyle: 'padding: 10px',
            layout: 'card',
            title: documentName,
            autoHeight: true,
           // url : 'StorageService',
           // frame:true,
            width:450,
            autoWidth: true,
            labelAlign: 'right',
            bbar: [{
                id: 'move-prev',
                text: 'Back',
                handler: function (btn) {
                    navigate(btn.up("panel"), "prev");
                },
                disabled: true
            }, '->', // greedy spacer so that the buttons are aligned to each side
            {
                id: 'finish',
                text: 'Finish',
                handler: function () {
                	 var form = this.up('form').getForm();
                	 if (form.isValid()) {
                         form.submit(
                         {
                        	 	success: function(form, action) {
       
                             },
                             failure: function(form, action) {
                                 Ext.Msg.alert('Failed', action.result.msg);
                             }
                         });
                     }
                    
                }
            ,
            disabled: true
            },
            {
                id: 'move-next',
                text: 'Next',
                handler: function (btn) {
                    navigate(btn.up("panel"), "next");
                }
            }],
            defaultType: "textfield"
            });
            
            
 var jsonPn = new Ext.form.Panel({
            bodyStyle: 'padding: 10px',
            layout:'card',
            title: "JSON editor",
           // autoHeight: true,
            url : 'convert',
           // frame:true,
            width:450,
            autoWidth: true,
            labelAlign: 'right',
            items: [
			{
            xtype:'textareafield',
            name: 'file',
            allowBlank:false
        }],
        bbar:[
        {
				text : 'Update',
				margin : '10 0 0 0',
					handler : function() {
						 var form = this.up('form').getForm();
                	 if (form.isValid()) {
                         form.submit(
                         {
                        	 	success: function(response, opts) {
							       		formPn.removeAll();
								
							       		var obj;
							       		obj = Ext.decode(opts.response.responseText);
							       		jsonResponse = opts.response.responseText;
									       var data = obj.data;
									       var length = obj.data.length;
									       active="card-";
									       active = active + (length-1);
									       for(var iter=0;iter<length;iter++)
									    	   {
									    	   var datef =Ext.ComponentManager.create(data[iter]);
									    	  formPn.add(datef);
									    	   }
									//Ext.getCmp('file').setValue(jsonResponse);
									Ext.getCmp('file').setValue(format(JSON.stringify(obj)));
                             },
                             failure: function(form, action) {
                                 Ext.Msg.alert('Failed', action.result.msg);
                             }
                         });
					}
			}},
			 {
				text : 'Show JSON',
				margin : '10 0 0 0',
					handler : function() {
							displayResponse();
              			
			}}]
            });
            

            
 
 
 
Ext.onReady(function() {
    var panel = Ext.create('Ext.Panel', {
        id:'main-panel',
        baseCls:'x-plain',
        renderTo: Ext.getBody(),
        layout: {
            type: 'table',
            columns: 3
        },
        // applied to child components
        defaults: {frame:true, width:500, height: 550},
        items:[jsonPn,formPn,disp]
    });
});
