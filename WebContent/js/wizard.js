var active="card-";

var getParameter = function(parameterName){
var link = window.location.toString();
var array = link.split("?");
var elements=array[1].split("&");
var length = elements.length;
for(var k=0;k<length;k++)
{
var value = elements[k].split("=");
if(value[0]==parameterName)
	return value[1].split("%20").join(" ");
}
return null;
};

var documentName = getParameter("documentName");
var documentId = getParameter("documentId"); 
var jsonUrl = "../docjson/"+documentId+".json";
document.title = documentName+" Form";

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

var formPn = new Ext.form.Panel({
            bodyStyle: 'padding: 10px',
            layout: 'card',
            title: documentName,
            autoHeight: true,
            url : '../user/StorageService',
            frame:true,
            width:500,
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
                         		params:{
								documentId:documentId
								},
                        	 	success: function(form, action) {
                                //Ext.Msg.alert('Success',Ext.decode(action.response.responseText).outputPath);
                                
                                // var obj = Ext.decode(action.response.responseText);
                               // var epoch = obj.epoch.toString();
                               // var userId = obj.userId.toString();
                                //var location = "./user/get_document_in_pdf?documentId="+documentId+"&epochTime="+epoch;
                             	//window.location=location;
                             	///window.open(location, '_blank');
                             	window.location="../user/home.html";
                        
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
var obj;
Ext.onReady(function(){	
	Ext.Ajax.request({
		   url: jsonUrl,
		   success: function(response, opts) {
			//   Ext.Msg.alert('Status', 'Changes saved successfully.');
		       obj = Ext.decode(response.responseText);
		       var data = obj.data;
		       var length = obj.data.length;
		       active = active + (length-1);
		       for(var iter=0;iter<length;iter++)
		    	   {
		    	   var datef =Ext.ComponentManager.create(data[iter]);
		    	  formPn.add(datef);
		    	   }
		       formPn.render('form-ct');
		      console.dir(obj);
		   },
		   failure: function(response, opts) {
			   Ext.Msg.alert('Status', 'Changes failed.');
		     
		   }
		});
});