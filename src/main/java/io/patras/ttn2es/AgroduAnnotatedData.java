package io.patras.ttn2es;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import org.json.JSONObject;
import org.thethingsnetwork.java.app.lib.Message;

import AgroduAnnotatedData.ValueType;

/**
 * @author ctranoris
 *
 */
public class AgroduAnnotatedData implements IAnnotatedData {

	/**
	 * 
	 */
	private JSONObject annotatedData;

	/** */
	private ValueType valueType;

	/**
	 * 
	 */
	public AgroduAnnotatedData() {

	}

	/**
	 * @param data
	 * @param vt
	 * @param string
	 * @param unit
	 */
	public AgroduAnnotatedData(final Message data, final ValueType vt, final double value, final String unit) {
		annotatedData = new JSONObject(data.toString());
		annotatedData.put("ValueType", vt.getDisplayName());
		annotatedData.put("Value", value);
		annotatedData.put("MUnit", unit);
		this.valueType = vt;

		DateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("UTC"));

		try {
			String time = data.getJSONObject("metadata").getString("time");
			String d = time.substring(0, 10);
			String t = time.substring(11, 19);
			Date timestamp = format.parse(d + t);
			annotatedData.put("timestamp", timestamp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.patras.ttn2es.IAnnotatedData#getJSONObject()
	 */
	public final JSONObject getJSONObject() {
		return annotatedData;
	}

	public final JSONObject setJSONObject(Message data) {

		return annotatedData;
	}

	/**
	 * @param payload
	 * @param data
	 * @return an Array of AgroduAnnotatedData[]
	 */
	public static AgroduAnnotatedData[] transformToAgroduAnnotatedData(final Message data, final String payload) {

		AgroduAnnotatedData[] ads = new AgroduAnnotatedData[3];
		ads[0] = new AgroduAnnotatedData(data, ValueType.TEMPERATURE,   Math.random()*10.0 + 20, "Celsius");
		ads[1] = new AgroduAnnotatedData(data, ValueType.HUMIDITY, Math.random()*10.0 + 60, "");
		ads[2] = new AgroduAnnotatedData(data, ValueType.SOILHYGROMETER, 1200.0, "");
		return ads;

	}

	public ValueType getValueType() {
		return valueType;
	}

	public void setValueType(ValueType valueType) {
		this.valueType = valueType;
	}

}
