window.onbeforeprint = function() {
	
	
   /*
    $("#_poActionHousingPrint").text($("#_planOfActionHousing").val());
    $("#_planDetailHousingPrint").text($("#_planDetailsHousing").val());
    $("#_referralPartnerHousingPrint").text($("#_referralPartnerHousing").val());
    $("#_aoHousingPrint").text($("#_aoHousing").val());
    $("#_aoDateHousingPrint").text($("#_inputDateTextAnticipatedHousing").val());
    
    
    $("#_poActionMMPrint").text($("#_planOfActionMM").val());
    $("#_planDetailMMPrint").text($("#_planDetailsMM").val());
    $("#_referralPartnerMMPrint").text($("#_referralPartnerMM").val());
    $("#_aoMMPrint").text($("#_aoMM").val());
    $("#_aoDateMMPrint").text($("#_inputDateTextAnticipatedMM").val());
    
    $("#_poActionEmpPrint").text($("#_planOfActionEmp").val());
    $("#_planDetailEmpPrint").text($("#_planDetailsEmp").val());
    $("#_referralPartnerEmpPrint").text($("#_referralPartnerEmp").val());
    $("#_aoEmpPrint").text($("#_aoEmp").val());
    $("#_aoDateEmpPrint").text($("#_inputDateTextAnticipatedEmp").val());
    
    $("#_poActionEduPrint").text($("#_planOfActionEdu").val());
    $("#_planDetailEduPrint").text($("#_planDetailsEdu").val());
    $("#_referralPartnerEduPrint").text($("#_referralPartnerEdu").val());
    $("#_aoEduPrint").text($("#_aoEdu").val());
    $("#_aoDateEduPrint").text($("#_inputDateTextAnticipatedEdu").val());
    
    $("#_poActionNSPrint").text($("#_planOfActionNS").val());
    $("#_planDetailNSPrint").text($("#_planDetailsNS").val());
    $("#_referralPartnerNSPrint").text($("#_referralPartnerNS").val());
    $("#_aoNSPrint").text($("#_aoNS").val());
    $("#_aoDateNSPrint").text($("#_inputDateTextAnticipatedNS").val());
    
    $("#_poActionHHPrint").text($("#_planOfActionHH").val());
    $("#_planDetailHHPrint").text($("#_planDetailsHH").val());
    $("#_referralPartnerHHPrint").text($("#_referralPartnerHH").val());
    $("#_aoHHPrint").text($("#_aoHH").val());
    $("#_aoDateHHPrint").text($("#_inputDateTextAnticipatedHH").val());

	$("#_poActionDHPrint").text($("#_planOfActionDH").val());
    $("#_planDetailDHPrint").text($("#_planDetailsDH").val());
    $("#_referralPartnerDHPrint").text($("#_referralPartnerDH").val());
    $("#_aoDHPrint").text($("#_aoHH").val());
    $("#_aoDateDHPrint").text($("#_inputDateTextAnticipatedDH").val());
    
    $("#_followUpNotesPrint").text($("#_followUpNotes").val());
    
    $("#_outAchHousing_P").text($("#_outAchHousing").val());
    $("#_achGoalHousing_P").text($("#_achGoalHousing").val());
    $("#_inputDateTextHousing_P").text($("#_inputDateTextHousing").val());
    
    $("#_outAchMM_P").text($("#_outAchMM").val());
    $("#_achGoalMM_P").text($("#_achGoalMM").val());
    $("#_inputDateTextMM_P").text($("#_inputDateTextMM").val());
    
    $("#_outAchEmp_P").text($("#_outAchEmp").val());
    $("#_achGoalEmp_P").text($("#_achGoalEmp").val());
    $("#_inputDateTextEmp_P").text($("#_inputDateTextEmp").val());
    
    $("#_outAchEdu_P").text($("#_outAchEdu").val());
    $("#_achGoalEdu_P").text($("#_achGoalEdu").val());
    $("#_inputDateTextEdu_P").text($("#_inputDateTextEdu").val());
    
    $("#_outAchNS_P").text($("#_outAchNS").val());
    $("#_achGoalNS_P").text($("#_achGoalNS").val());
    $("#_inputDateTextNS_P").text($("#_inputDateTextNS").val());
    
    $("#_outAchHH_P").text($("#_outAchHH").val());
    $("#_achGoalHH_P").text($("#_achGoalHH").val());
    $("#_inputDateTextHH_P").text($("#_inputDateTextHH").val());

	 $("#_outAchDH_P").text($("#_outAchDH").val());
    $("#_achGoalDH_P").text($("#_achGoalDH").val());
    $("#_inputDateTextDH_P").text($("#_inputDateTextDH").val());
    
    
    */
    
   
};
    
jQuery(document).ready(function() {
	
	//populateEachCheckboxFromJsonData();
/*
    
    validateAndShowMessage();  
    var resId = jQuery.urlParam('residentId');
    var onThisDate = jQuery.urlParam('onThisDate');
    var currHref = jQuery("#_loadActionPlan").attr('href');
    var prefix = currHref.split('&');
    
    if(jQuery("#_dates").val() != 'new') {
	jQuery('#_loadActionPlan').attr('href', prefix[0] + '&residentId='+resId + '&onThisDate='+onThisDate );
    }else{	
	jQuery('#_loadActionPlan').attr('href', prefix[0] + '&residentId='+resId);
    }

    jQuery.ajax({
	type : "POST",
	contentType : "application/json",
	url : "/getAllLatestScoreGoal?residentId=" + jQuery('#_residentIdText').text(),
	dataType : 'json',
	cache : false,
	timeout : 600000,
	success : function(data) {
	    // Following method highlight background in actionPlan SSM Grid as
	    // per Current Score of resident.
	    highlightSSMGridAsPerCurrentScore(data);
	    // This method is useful in case of modify use case
	    populateEachActionPlanFromJsonData();

	},
	error : function(e) {
	    console.log("ERROR : ", e);
	}
    });

    // set current date
    $("#_currentDate").text(new Date().toLocaleDateString());
    */
    
    
});

/*
function populateEachCheckboxFromJsonData() {
	 

	   // if (jQuery("#_planOfActionString").val() != '') {
	    	var residentListJson = JSON.parse(jQuery("_#rList").val());
	    	
		
	      	for(var x=0; x < residentListJson.length; x++)   // comparison should be "<" not "<="
	    	{
	    		document.getElementById(x).checked = residentListJson[x].checked;
	    	}
	    	
	    //}

	}
*/



/**
 * This method helps in populates action plan fields for existing saved actionPlan. Get Values from database and put it in each Box
 */
function buildEachJSONString() {
 //https://stackoverflow.com/questions/63322484/how-to-get-the-value-of-checked-checkbox-in-vanilla-javascript
    if (jQuery("#_checkboxList").val() == '') {
    	//var checkboxListJson = JSON.parse(jQuery("#_checkboxList").val());
    	
    	json = '{';
    	
    	document.getElementsByName('list').forEach(function(chk){
    		  chk.addEventListener('click', function() {
    		    if(this.checked){
    		    	json = json + document.getElementById(this.value) + ",";
    		      //console.log(this.value);
    		    }
    		  });
    		});
    	
    	json = json + '}';
    	json = "testing from eventDetails-js";
    	document.getElementById('_checkboxList').value = 'testingtestingtesting';
    	jQuery("#_checkboxList").val(json);
    	
    	
    	/*i = 0;
    	found = true;
    	
    	while (found) {
    		checkbox = document.getElementById(i);
    		if (checkbox.checked == true) {
    			json = json + "\"i\",";
    		}	
    		found = true;
        	i++;
    	}
    	
    	json = '{' + json.slice(0, -1) + '}';
    	jQuery("#_checkboxList").val(json);
    	alert(json);
    	*/
    }
}

function submitCheckboxList() {
	json = constructJsonFromData();
	jQuery("#_checkboxList").val(json);
}