package Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
    private HashMap<String, ArrayList<String>> phoneBook;// = new HashMap<>();

    public PhoneBook() {
        this.phoneBook = new HashMap<>();
    }

    void add(String name, String phoneNum) {
        ArrayList<String> phones;
        if (this.phoneBook.containsKey(name)) {
            phones = this.phoneBook.get(name);
        } else {
            phones = new ArrayList<>();
        }

        phones.add(clrPhone(phoneNum));
        this.phoneBook.put(name, phones);
    }

    public String get(String name) {
        String result = "";
        if (phoneBook.isEmpty())
            return result;

        if (this.phoneBook.containsKey(name)) {
            result = phoneBook.get(name).toString();
        }
        else
            result = "Not found";

        return result;
    }

    private String clrPhone(String phoneNum) {
        return phoneNum.replaceAll("\\D","");
    }

    public void printPhoneBook (){
        System.out.println("PhoneBook has " + phoneBook.size() + " reords:");
        for (Map.Entry<String, ArrayList<String>> o : this.phoneBook.entrySet()) {
            System.out.printf("%s: %s\n", o.getKey(), o.getValue().toString());
        }

    };
}
