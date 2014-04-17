package com.extjs.formbuilder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.*;

public class JsonConvert {
	private static final Set<String> configForTextField = new HashSet<String>(
			Arrays.asList(new String[] { "vtype", "fieldLable", "emptyText",
					"maxLength", "minLength", "vtypeText" }));
	
	public static String getType(String input)
	{
		input = input.trim();
		if(input.equals("true") || input.equals("false"))
				return "boolean";
		try{
			Integer.parseInt(input.trim());
			return "int";
		}
		catch(Exception e)
		{
			return "string";
		}
	}
	

	private static JSONObject createContainer(int card) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("id", "card-" + card);
		obj.put("xtype", "fieldcontainer");
		return obj;
	}

	private static JSONObject createTextfield(String name, String label)
			throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("xtype", "textfield");
		obj.put("fieldLabel", label);
		return obj;
	}

	private static JSONObject createTextareafield(String name, String label)
			throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("xtype", "textareafield");
		obj.put("fieldLabel", label);
		return obj;
	}

	private static JSONObject createDatefield(String name, String label)
			throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("xtype", "datefield");
		obj.put("fieldLabel", label);
		return obj;
	}

	private static JSONObject htmlField(String html) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("html", html);
		return obj;
	}
	
	private static JSONObject createNumberField(String name, String label)
			throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("xtype", "numberfield");
		obj.put("fieldLabel", label);
		return obj;
	}
	
	

	private static boolean isValidate(String currentLine) {
		try {
			currentLine = currentLine.trim();
			String start = currentLine.split(":", 2)[0];
			String part2 = currentLine.split(":", 2)[1];
			String config[] = part2.split(",");
			start = start.trim();
			// .out.println("In isValid : ["+start+"]");
			if (!(start.matches("\\d+") || start.matches("question") || start
					.matches("tip")))
				return false;
			for (int i = 2; i < config.length; i++) {
				//---------------------------------------------------------------------
				String configItem[] = config[i].split(":");
				//if (!configForTextField.contains(configItem[0]))
				//	return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String convertStringToJSON(String file) throws JSONException {

		int card = 0;
		int line = 0;
		Set<Integer> errorLines = new HashSet<Integer>(); /*
														 * To keep track of
														 * error message
														 */
		JSONObject output = new JSONObject(); /* Output in json */
		String lines[] = file.split("\\r?\\n");

		JSONArray date = new JSONArray();
		JSONObject currentContainer = createContainer(card);
		JSONArray items = new JSONArray();
		for (String currentLine : lines) {
			if (currentLine.startsWith("===")) {
				currentContainer.put("items", items);
				date.put(currentContainer);
				card++;
				currentContainer = createContainer(card);
				items = new JSONArray();
			} else if (currentLine.startsWith("end")) {
				currentContainer.put("items", items);
				date.put(currentContainer);
				items = new JSONArray();
			} else {
				currentLine = currentLine.trim();
				if (isValidate(currentLine)) {
					JSONObject obj = null;
					String start = currentLine.split(":", 2)[0];
					String part2 = currentLine.split(":", 2)[1];
					String config[] = part2.split(",");
					start = start.trim();
					if ((start.matches("\\d+"))) {
						// System.out.println("config [0] "+config[0]);
						if (config[0].trim().equals("text")) {
							obj = createTextfield(start, config[1]);
						}
						if (config[0].trim().equals("textarea")) {
							obj = createTextareafield(start, config[1]);
						}
						if (config[0].trim().equals("date")) {
							obj = createDatefield(start, config[1]);
						}
						if (config[0].trim().equals("number")) {
							obj = createNumberField(start, config[1]);
						}

						for (int i = 2; i < config.length; i++) {
							String configItem[] = config[i].split(":");
							//-----------------------------------------------------
							if(getType(configItem[1].trim()).equals("boolean"))
								obj.put(configItem[0].trim(), configItem[1].trim().equals("true"));
							if(getType(configItem[1].trim()).equals("int"))
								obj.put(configItem[0].trim(), Integer.parseInt(configItem[1].trim()));
							if(getType(configItem[1].trim()).equals("string"))
								obj.put(configItem[0].trim(), configItem[1].trim());
	
								
							//-----------------------------------------------------
							
							//obj.put(configItem[0].trim(), configItem[1].trim());
							// putting properties 
						}
						// System.out.println("line : "+line + "obj :"+obj);
						items.put(obj);
					} else if ((start.matches("question"))) {
						items.put(htmlField("<strong>" + part2 + "</strong>"));
					} else if ((start.matches("tip"))) {
						items.put(htmlField("<p>" + part2 + "</p>"));
					}
				} else
					errorLines.add(line);
			}

			line++;
		}

		Iterator<Integer> it = errorLines.iterator();
		JSONArray errorLineNumbers = new JSONArray();
		while (it.hasNext()) {
			errorLineNumbers.put(it.next());
		}
		output.put("success", true);
		output.put("data", date);
		output.put("errorLines", errorLineNumbers);
		return output.toString();
	}
}
