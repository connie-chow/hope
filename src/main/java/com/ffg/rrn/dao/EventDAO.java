/**
 * 
 */
package com.ffg.rrn.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ffg.rrn.mapper.EventMapper;
import com.ffg.rrn.mapper.ResidentMapper;
import com.ffg.rrn.model.CategoryPercentage;
import com.ffg.rrn.model.Dashboard;
import com.ffg.rrn.model.Event;
import com.ffg.rrn.model.Property;
import com.ffg.rrn.model.QuarterCount;
import com.ffg.rrn.model.QuestionChoice;
import com.ffg.rrn.model.Resident;
import com.ffg.rrn.model.ResidentScoreGoal;

/**
 * @author FFGRRNTeam
 *
 *
 */
@Repository
@Transactional
public class EventDAO extends JdbcDaoSupport {

	//private final static String SQL_INSERT_EVENT = "INSERT INTO EVENTS (NAME, DESCRIPTION, DATE_ADDED, SERVICE_COORD) VALUES (?,?,?,?)";
	private final static String SQL_INSERT_EVENT = "INSERT INTO EVENTS (NAME, DATE_ADDED, DESCRIPTION) VALUES (?,?,?)";

	//private final static String SQL_UPDATE_EVENT = "UPDATE EVENTS SET (NAME, DATE_ADDED, ATTENDEES, DESCRIPTION) WHERE EVENT_ID = ? VALUES (?,?,?,?,?)";
	private final static String SQL_UPDATE_EVENT = "UPDATE EVENTS SET NAME=?, DATE_ADDED=?, ATTENDEES=?, DESCRIPTION=? WHERE EVENT_ID = ?";

	private final static String SQL_SELECT_EVENTS = "SELECT * FROM EVENTS";
	
	/*
	private final static String SQL_CHANGE_STATUS_OF_RESIDENT = "UPDATE RESIDENT SET ACTIVE=?, DATE_MODIFIED=?, MODIFIED_BY=? "
			+ " WHERE RESIDENT_ID=?";

	private final static String SQL_INSERT_CHILD = "INSERT INTO CHILD ( FULL_NAME, PARENT_ID, PVR_FLAG) VALUES ( ?,?,?)";

	private final static String SQL_DELETE_CHILD = "DELETE FROM CHILD WHERE PARENT_ID=?";

	private final static String SQL_INSERT_RESIDENT_ASSESSMENT_QUES = "INSERT INTO RESIDENT_ASSESSMENT_QUESTIONNAIRE ( RESIDENT_ID, QUESTION_ID, CHOICE_ID, LIFE_DOMAIN, ON_THIS_DATE) "
			+ "VALUES ( ?, ?, ?, ?, ?)";

	private final static String SQL_INSERT_RESIDENT_SCORE_GOAL = "INSERT INTO RESIDENT_SCORE_GOAL ( RESIDENT_ID, LIFE_DOMAIN, SCORE, GOAL, ON_THIS_DATE) "
			+ "VALUES ( ?, ?, ?, ?, ?)";

	private final static String SQL_UPDATE_RESIDENT_ASSESSMENT_QUES = "UPDATE RESIDENT_ASSESSMENT_QUESTIONNAIRE SET RESIDENT_ID = ?, QUESTION_ID = ?, CHOICE_ID = ?, LIFE_DOMAIN = ? WHERE RESIDENT_ID = ? and LIFE_DOMAIN = ? and ON_THIS_DATE = ? AND QUESTION_ID = ?";

	private final static String SQL_UPDATE_RESIDENT_SCORE_GOAL = "UPDATE RESIDENT_SCORE_GOAL SET RESIDENT_ID = ?, LIFE_DOMAIN = ?, SCORE = ?, GOAL = ? WHERE RESIDENT_ID = ? and LIFE_DOMAIN = ? and ON_THIS_DATE = ?";
*/
	
	@Autowired
	public EventDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	
	public List<Event> getAllEvents() {
		List<Event> eventList = this.getJdbcTemplate().query("SELECT * FROM EVENTS",
				new BeanPropertyRowMapper<Event>(Event.class));
		return eventList;
	}
	
	
	public Event getEventById(long id) {
		String sql_select_event = "SELECT EVENT_ID, NAME, DESCRIPTION, DATE_ADDED, ATTENDEES FROM EVENTS WHERE EVENT_ID = ?";
		System.out.println("sql_select_event = " + sql_select_event);
		EventMapper rowMapper = new EventMapper();
		Event event = (Event) this.getJdbcTemplate().queryForObject(
				sql_select_event,
				new Object[] { id }, new BeanPropertyRowMapper(Event.class));
		//Event event = this.getJdbcTemplate().query(sql_select_event, new BeanPropertyRowMapper<Event>(Event.class));
		System.out.println("EventDAO event = " + event.toString());
		return event;
	}
	
	
	
	private PreparedStatement buildSelectEventPS(Connection connection, String[] pkColumnNames)
			throws SQLException {
		PreparedStatement ps = connection.prepareStatement(SQL_SELECT_EVENTS, pkColumnNames);
		return ps;
	}

	/*
	public Long updateAttendees(Event event) {

		Long eventId = event.getEventId();

		// Logic on when to insert vs update existing Resident
		if (eventId == null || eventId == 0) {
			//eventId = insertNewAttendees(event);
		} else {
			eventId = updateExistingAttendees(event);
		}

		return eventId;
	}
	*/
	
	public Long saveEvent(Event event) {
		System.out.println("EventDAO - saveEvent()");
		Long eventId = event.getEventId();
		System.out.println("EventDAO.saveEvent() - event.toString() = " + event.toString());
		// Logic on when to insert vs update existing Resident
		if (eventId == null || eventId == 0) {
			eventId = insertNewEvent(event);
		} else {
			System.out.println("EventDAO - saveEvent() - updateExistingREsident");
			eventId = updateExistingEvent(event);
		}

		return eventId;
	}

	
	private long insertNewEvent(Event event) {
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] pkColumnNames = new String[] { "event_id" };
		this.getJdbcTemplate().update(conn -> {
			try {
				return buildInsertEventPS(conn, event, pkColumnNames);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}, keyHolder);

		long newEventId = keyHolder.getKey().longValue();
		System.out.println("insertNewEvent = keyHolder.getKey() = " + newEventId);
		event.setEventId(newEventId);
		System.out.println("new event id already set!");
		if (newEventId > 0) {
			//insertNewChildren(event);
		}

		return newEventId;
	}
	
	
	private long updateExistingEvent(Event event) {
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] pkColumnNames = new String[] { "event_id" };
		this.getJdbcTemplate().update(conn -> {
			try {
				return buildUpdateEventPS(conn, event, pkColumnNames);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}, keyHolder);

		long newEventId = keyHolder.getKey().longValue();
		System.out.println("insertNewEvent = keyHolder.getKey() = " + newEventId);
		event.setEventId(newEventId);
		System.out.println("new event id already set!");
		if (newEventId > 0) {
			//insertNewChildren(event);
		}

		return newEventId;
	}
	
	/*
	
	
	private long updateExistingAttendees(Event event) {
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] pkColumnNames = new String[] { "event_id" };
		this.getJdbcTemplate().update(conn -> {
			try {
				return buildUpdateAttendeesPS(conn, event, pkColumnNames);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}, keyHolder);

		long newEventId = keyHolder.getKey().longValue();
		System.out.println("insertNewEvent = keyHolder.getKey() = " + newEventId);
		event.setEventId(newEventId);
		System.out.println("new event id already set!");
		if (newEventId > 0) {
			//insertNewChildren(event);
		}

		return newEventId;
	}
	
	*/
	
	private java.sql.Date parseMyDate(String selectedDate) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		java.util.Date parsed = format.parse(selectedDate);
		return new java.sql.Date(parsed.getTime());
	}
	
	
	private PreparedStatement buildInsertEventPS(Connection connection, Event event, String[] pkColumnNames)
			throws SQLException, ParseException {
		PreparedStatement ps = connection.prepareStatement(SQL_INSERT_EVENT, pkColumnNames);
		System.out.println("1");
		System.out.println("event passed to SQL: ");
		System.out.println("event name = " + event.getName());
		System.out.println("event description = " + event.getDescription());
		ps.setString(1, StringUtils.capitalize(event.getName().trim().toLowerCase()));
		System.out.println("2");
		ps.setString(2,  event.getDateAdded());
		ps.setString(3, StringUtils.capitalize(event.getDescription().trim().toLowerCase()));
		//ps.setString(2, "test description");
		
		System.out.println(ps.toString());
		//ps.setDate(3, parseMyDate(event.getDateAdded()));
		//ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
		System.out.println("4");
		//ps.setString(4, event.getServiceCoord());
		System.out.println("5");
		return ps;
	}
	
	
	private PreparedStatement buildUpdateEventPS(Connection connection, Event event, String[] pkColumnNames)
			throws SQLException, ParseException {
		// "UPDATE EVENTS SET (NAME, DATE_ADDED, ATTENDEES, DESCRIPTION) WHERE EVENT_ID = ? VALUES (?,?,?,?)";

		PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_EVENT, pkColumnNames);
		System.out.println("1-buildUpdateAttendeesPS");
		System.out.println("event passed to SQL: ");
		System.out.println("event name = " + event.getName());
		System.out.println("event description = " + event.getDescription());
		System.out.println("event attendees = " + event.getResidentList());
		System.out.println("event.toString() = " + event.toString());
		ps.setString(1, event.getName());
		System.out.println("2");
		ps.setString(2, event.getDateAdded());
		ps.setString(3, event.getResidentList());
		ps.setString(4,  event.getDescription());
		ps.setLong(5, event.getEventId());
		
		System.out.println(ps.toString());
	
		return ps;
	}




}
