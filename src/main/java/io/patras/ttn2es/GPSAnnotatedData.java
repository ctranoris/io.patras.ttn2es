package io.patras.ttn2es;

import org.json.JSONObject;
import org.thethingsnetwork.java.app.lib.Message;

import AgroduAnnotatedData.ValueType;

/**
 * @author ctranoris
 *
 */
public class GPSAnnotatedData implements IAnnotatedData {


	/** */
	private ValueType valueType;
	
	/**
	 * 
	 */
	private JSONObject annotatedData;
	/* (non-Javadoc)
	 * @see io.patras.ttn2es.IAnnotatedData#getJSONObject()
	 */	
	public final JSONObject getJSONObject() {
		// TODO Auto-generated method stub
		return annotatedData;
	}
	
	/**
	 * @param data
	 * @param vt
	 * @param string
	 */
	public GPSAnnotatedData(final Message data, final ValueType vt, final double lat, final double lon) {
		annotatedData = new JSONObject(data.toString());
		annotatedData.put("ValueType", vt.getDisplayName());
		JSONObject latlon = new JSONObject();
		latlon.put("Latitude", lat);
		latlon.put("Longitude", lon);
		annotatedData.put("Value", latlon);
		this.valueType = vt;

	}

	/**
	 * @param data
	 * @param payload
	 * @return
	 */
	public static GPSAnnotatedData[] transformToGPSAnnotatedData(Message data, String payload ) {
		GPSAnnotatedData[] ads = new GPSAnnotatedData[1];
		double lat = 38.22222;
		double lon = 21.7506;
		ads[0] = new GPSAnnotatedData(data, ValueType.GPSLOCATION, lat, lon);
		return ads;
	}
	

	public ValueType getValueType() {
		return valueType;
	}


	public void setValueType(ValueType valueType) {
		this.valueType = valueType;
	}

}
