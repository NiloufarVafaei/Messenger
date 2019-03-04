/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactsapp;

import Entity.Contact;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author niloufar
 */
public class Opreation {

    public int AddContact(Contact c) {
        Contact findContact = this.Find(c.getMobile());
        if (findContact == null) {
            JSONParser jsonParser = new JSONParser();
            try {
                File file = new File("contact.json");
                if (!file.exists()) {
                    file.createNewFile();
                }
                Object obj = null;
                try {
                    obj = jsonParser.parse(new FileReader(file));//exception happens here.
                } catch (Exception e) {
                }

                JSONArray jsonArray;
                if (obj == null) {
                    jsonArray = new JSONArray();
                } else {
                    jsonArray = (JSONArray) obj;
                }

                JSONObject contact = new JSONObject();
                contact.put("name", c.getName());
                contact.put("mobile", c.getMobile());
                contact.put("homeNumber", c.getHomeNumber());
                contact.put("IP", c.getIP());
                jsonArray.add(contact);
                FileWriter fileW = new FileWriter("contact.json");
                fileW.write(jsonArray.toJSONString());
                fileW.flush();
                fileW.close();
                return 1;

            } catch (Exception e) {
                return -1;
            }
        } else {
            return 0;
        }
    }

    public Contact Find(long mobile) {
        JSONArray contactJsonArray = null;
        try {
            JSONParser parser = new JSONParser();

            File file = new File("contact.json");
            if (!file.exists()) {
                return null;
            }

            try {
                contactJsonArray = (JSONArray) parser.parse(new FileReader(file));//exception happens here.
            } catch (Exception e) {
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (contactJsonArray == null) {
            return null;
        }

        for (Iterator it = contactJsonArray.iterator(); it.hasNext();) {
            JSONObject c = (JSONObject) it.next();
            if (c.get("mobile").equals(mobile)) {
                Contact returnValue = new Contact();
                returnValue.setName(c.get("name").toString());
                returnValue.setMobile((Long) c.get("mobile"));
                returnValue.setHomeNumber((Long) c.get("homeNumber"));
                returnValue.setIP(c.get("IP").toString());
                return returnValue;
            }
        }
        return null;
    }

    public ArrayList<Contact> Find(String name) {
        JSONArray contactJsonArray = null;
        try {
            JSONParser parser = new JSONParser();
            File file = new File("contact.json");
            if (!file.exists()) {
                return null;
            }
            try {
                contactJsonArray = (JSONArray) parser.parse(new FileReader(file));//exception happens here.
            } catch (Exception e) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (contactJsonArray == null) {
            return null;
        }
        ArrayList<Contact> returnList = new ArrayList();
        for (Iterator it = contactJsonArray.iterator(); it.hasNext();) {
            JSONObject c = (JSONObject) it.next();
            if (c.get("name").toString().toLowerCase().equals(name.toLowerCase())) {
                Contact FindValue = new Contact();
                FindValue.setName(c.get("name").toString());
                FindValue.setMobile((Long) c.get("mobile"));
                FindValue.setHomeNumber((Long) c.get("homeNumber"));
                FindValue.setIP(c.get("IP").toString());
                returnList.add(FindValue);
            }
        }
        return returnList;
    }

//    public void Initial()
//    {
//        JSONArray contactJsonArray = null;
//                try {
//             JSONParser parser = new JSONParser();
//             File file = new File("contact.json");
//             FileReader fr = new FileReader(file.getName());
//             contactJsonArray = (JSONArray) parser.parse(fr);
//             System.out.println(contactJsonArray.toString());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    // }
    public int Delete(String name) {
        ArrayList<Contact> findContact = this.Find(name);
        if (findContact.size() != 0) {
            JSONParser jsonParser = new JSONParser();
            try {
                File file = new File("contact.json");
                if (!file.exists()) {
                    return 0;
                }
                Object obj = null;
                try {
                    obj = jsonParser.parse(new FileReader(file));//exception happens here.
                } catch (Exception e) {
                }
                JSONArray jsonArray;
                JSONArray jsonArrayFind = new JSONArray();
                if (obj == null) {
                    jsonArray = new JSONArray();
                } else {
                    jsonArray = (JSONArray) obj;
                }

                for (Contact c : findContact) {
                    JSONObject contact = new JSONObject();
                    contact.put("name", c.getName());
                    contact.put("mobile", c.getMobile());
                    contact.put("homeNumber", c.getHomeNumber());
                    contact.put("IP", c.getIP());
                    jsonArrayFind.add(contact);
                }
                jsonArray.removeAll(jsonArrayFind);
                FileWriter fileW = new FileWriter("contact.json");
                fileW.write(jsonArray.toJSONString());
                fileW.flush();
                fileW.close();
                return 1;

            } catch (Exception e) {
                return -1;
            }
        }
        return 0;
    }

    public <T> ArrayList<Contact> Search(T input) {
        JSONArray contactJsonArray = null;
        ArrayList<Contact> returnList = new ArrayList();
        try {
            JSONParser parser = new JSONParser();

            File file = new File("contact.json");
            if (!file.exists()) {
                return null;
            }
            try {
                contactJsonArray = (JSONArray) parser.parse(new FileReader(file));//exception happens here.
                if (input.equals("")) {
                    for (Iterator it = contactJsonArray.iterator(); it.hasNext();) {
                        JSONObject c = (JSONObject) it.next();
                        Contact FindValue = new Contact();
                        FindValue.setName(c.get("name").toString());
                        FindValue.setMobile((Long) c.get("mobile"));
                        FindValue.setHomeNumber((Long) c.get("homeNumber"));
                        FindValue.setIP(c.get("IP").toString());
                        returnList.add(FindValue);

                    }
                    return returnList;
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (contactJsonArray == null) {
            return null;
        }

        if (input instanceof java.lang.String) {
            for (Iterator it = contactJsonArray.iterator(); it.hasNext();) {
                JSONObject c = (JSONObject) it.next();
                if (c.get("name").toString().toLowerCase().contains((CharSequence) input.toString().toLowerCase())) {
                    Contact FindValue = new Contact();
                    FindValue.setName(c.get("name").toString());
                    FindValue.setMobile((Long) c.get("mobile"));
                    FindValue.setHomeNumber((Long) c.get("homeNumber"));
                    FindValue.setIP(c.get("IP").toString());
                    returnList.add(FindValue);
                }
            }
        } else if (input instanceof java.lang.Long || input instanceof java.lang.Integer) {
            for (Iterator it = contactJsonArray.iterator(); it.hasNext();) {
                JSONObject c = (JSONObject) it.next();
                if (c.get("mobile").toString().contains((CharSequence) input.toString())) {
                    Contact FindValue = new Contact();
                    FindValue.setName(c.get("name").toString());
                    FindValue.setMobile((Long) c.get("mobile"));
                    FindValue.setHomeNumber((Long) c.get("homeNumber"));
                    FindValue.setIP(c.get("IP").toString());
                    returnList.add(FindValue);
                }
            }
        }
        return returnList;
    }

    public int ImportJson(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            return -1;
        } else {
            try {
                Object obj = null;
                JSONParser jsonParser = new JSONParser();
                try {
                    obj = jsonParser.parse(new FileReader(file));//exception happens here.
                } catch (Exception e) {

                }
                if (obj == null) {
                    return -1;
                }
                JSONArray jsonArray;
                jsonArray = (JSONArray) obj;
                //read source file
                File fileSource = new File("contact.json");
                if (!fileSource.exists()) {
                    fileSource.createNewFile();
                }
                Object objSource = null;
                try {
                    objSource = jsonParser.parse(new FileReader(fileSource));//exception happens here.
                } catch (Exception e) {
                }

                JSONArray jsonArraySource;
                if (objSource == null) {
                    jsonArraySource = new JSONArray();
                } else {
                    jsonArraySource = (JSONArray) objSource;
                }

                //------------
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jo = (JSONObject) jsonArray.get(i);
                    if (jo.get("name") != null && jo.get("mobile") != null) {
                        JSONObject jbSource = new JSONObject();
                        jbSource.put("name", jo.get("name"));
                        jbSource.put("mobile", jo.get("mobile"));
                        jbSource.put("homeNumber", jo.get("homeNumber"));
                        jbSource.put("IP", jo.get("IP"));
                        if (!JsonobjExist(jsonArraySource, jbSource)) {
                            jsonArraySource.add(jbSource);
                        }
                    }
                }
                FileWriter fileW = new FileWriter("contact.json");
                fileW.write(jsonArraySource.toJSONString());
                fileW.flush();
                fileW.close();
                return 1;
            } catch (Exception e) {
                return -1;
            }
        }
    }

    public boolean JsonobjExist(JSONArray js, JSONObject jc) {
        for (Iterator it = js.iterator(); it.hasNext();) {
            JSONObject c = (JSONObject) it.next();
            if (c.get("mobile").equals(jc.get("mobile"))) {
                return true;
            }
        }
        return false;
    }

    public String getIPByName(String name) {
        ArrayList<Contact> returnList = Find(name);
        if (returnList == null) {
            return "";
        } else {
            String IP = returnList.get(0).getIP();

            return IP;
        }
    }

}
