/**
 * 
 */
package com.ffg.rrn.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ffg.rrn.model.Event;
import com.ffg.rrn.model.Resident;

/**
 * @author FFGRRNTEam
 * 
 *
 *
 */
public class EventMapper implements RowMapper<Event> {

	public static final String RESIDENT_SQL //
			= "select r.RESIDENT_ID, r.ACTIVE, r.IS_RESIDENT, r.FIRST_NAME, r.MIDDLE, r.LAST_NAME, r.PROP_ID,r.VIA_VOICEMAIL, r.VOICEMAIL_NO, r.VIA_TEXT, r.TEXT_NO, r.VIA_EMAIL, r.EMAIL, r.ADDRESS, r.ACK_PR, "
					+ " r.ALLOW_CONTACT, r.WANTS_SURVEY, r.PHOTO_RELEASE, r.SERVICE_COORD, r.REF_TYPE, r.A_TYPE, "
					+ " r.date_added, r.date_modified, r.modified_by, p.prop_name, ref.ref_value, a.a_value, "
					+ " r.AGE, r.GENDER, r.PRI_LANGUAGE, r.MARITAL_STATUS,  r.ANNUAL_GROSS, r.ETHNICITY, r.RACE, r.H_O_H, r.VETERAN, r.DISABILITY, r.RC_OR_EX_OFF, r.SSI, r.SSDI, "
					+ " r.HEALTH_COVERAGE, r.HIGHEST_EDU, r.SAFE_DAY, r.SAFE_NIGHT, OCCUPANCY_LENGTH, MODE_TRANSPORT, EXP_FOOD_SHORT, INTERNET_ACCESS, HOH_TYPE, INT_RES_COUNCIL, UNEMP_REASON, BARRIER_TO_EDU, HEALTH_CONDITION, PROGRAM_SRVC_YOUTH, PROGRAM_SRVC_ADULT,   "
					+ " (select string_agg(full_name || ' (' || PVR_FLAG || ')', ', ') from child where parent_id = r.resident_id) as children, "
					+ " ap.referral_partner , ap.anticipated_date , ap.plan_of_action, ap.plan_details, ap.anticipated_outcomes,  ap.followup_notes, ap.outcome_achieved, ap.achieved_ssm, ap.completion_date, ap.date_added as apDateAdded, ap.date_modified as apDateModified, "
					+ " cn.description, cn.assessment, cn.plan, cn.no_show_date, cn.date_added as cnDateAdded, cn.date_modified as cnDateModified,"
					+ " rf.INTERPRETATION, rf.REFERRED_BY, rf.REFERRAL_REASON, rf.COMMENTS, rf.PREVIOUS_ATTEMPTS, rf.RF_FOLLOWUP_NOTES, rf.RES_APP_SCHEDULED, rf.date_added as rfDateAdded, rf.date_modified as rfDateModified"
					+ " from Resident r join referral ref on ref.ref_id = r.ref_type"
					+ " join property p on p.prop_id = r.prop_id"
					+ " left join action_plan ap on ap.resident_id = r.resident_id"
					+ " left join case_notes cn on cn.resident_id = r.resident_id"
					+ " left join referral_form rf on rf.resident_id = r.resident_id"
					+ " left join assessment_type a on a.a_id = r.a_type ";
	
	@Override
	public Event mapRow(ResultSet rs, int row) throws SQLException {

		Event r = new Event();
		r.setEventId(rs.getLong("EVENT_ID"));
		r.setName(rs.getString("NAME"));
		r.setDescription(rs.getString("DESCRIPTION"));
		r.setDateAdded(rs.getString("DATE_ADDED"));
		r.setResidentList(rs.getString("ATTENDEES"));
		return r;

	}

}
