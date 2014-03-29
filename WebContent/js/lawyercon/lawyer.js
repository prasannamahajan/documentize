Ext.onReady(function() {
	Ext.QuickTips.init();
	 var uri='<img src="../resources/images/rushi.jpg" />';
var formpanel=Ext.create('Ext.form.Panel', {
	        //width: 250,
	        height: 400,
	        bodyPadding: 10,
	       
	        items: [{
	        	html:uri,
	               id:'0'
	        	},{
	            xtype: 'displayfield',
	            fieldLabel: 'Name',
	            name: 'Name',
	            id:'1'
	        }, {
	            xtype: 'displayfield',
	            fieldLabel: 'Address',
	            name: 'addr',
	            id:'2',
	            	
	            }, {
	            xtype: 'displayfield',
	            fieldLabel: 'Phone Number',
	            name: 'phnum',
	            id:'3'
	        },
	        {
	        	xtype:'displayfield',
	            fieldLabel:'Email-id',
	        	name:'email',
	        	id:'4'
	        }
	        ],
	      
	    });
          formpanel.render('form-ct');
          Ext.Ajax.request({
    		  url : 'lawyerprof',
    		  method: 'POST',
    		  headers: { 'Content-Type': 'application/json' },                     
    		  params : { "test" : "testParam" },
    		  success: function (response) {
    		         var jsonResp = Ext.JSON.decode(response.responseText);
    		         var address=jsonResp.data.lawyeraddress;
    		         var name=jsonResp.data.lawyername;
    		         var eid=jsonResp.data.lawyerid;
    		         var phonenumber=jsonResp.data.lawyernumber;
    		          Ext.getCmp('1').setValue(name);
    		          Ext.getCmp('2').setValue(address);
    		          Ext.getCmp('3').setValue(phonenumber);
    		          Ext.getCmp('4').setValue(eid);
    		          
                    
    		       },
    		  failure: function (response) {
    		      var jsonResp = Ext.util.JSON.decode(response.responseText);
    		      Ext.Msg.alert("Error",jsonResp.error);
    		     
    		       }
    		 });       
});

	