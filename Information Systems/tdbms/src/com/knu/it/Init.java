package com.knu.it;

import com.knu.it.db.Database;
import com.knu.it.db.Table;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Init {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        byte[] readline = new byte[80];
        int codeAction, textLen;
        Database db = new Database("Untitled", "Untitled");
        Table t = new Table("Untitled", "Untitled", "Untitled");
        boolean db_initialized = false;
        boolean t_initialized = false;
        String[] menu = {"+1.  Прочитати базу даних з диску",
                         "+2.  Прочитати таблицю з поточної бази даних",
                         "+3.  Створити базу",
                         "-4.  Створити таблицю в поточній базі",
                         "-5.  Видалити таблицю з поточної бази",
                         "-6.  Редагувати поточну таблицю в базі"
        };
        do {
            String upr;
            for (String ss : menu) System.out.println(ss); // вивести меню
            System.out.println("Введіть код дії або end:");
            do {
                try {
                    textLen = System.in.read(readline);
                    upr = new String(readline, 0, textLen, "ISO-8859-1");
                    if (upr.trim().equals("end")) return;
                    codeAction = new Integer(upr.trim());
                } catch (Exception ee) {
                    System.out.println("Невірний код дії, повторіть: ");
                    continue;
                }
                if (codeAction >= 1 && codeAction <= menu.length) {
                    break;
                } else {
                    System.out.println("Невірний код дії, повторіть: ");
                    continue;
                }
            } while (true);

            switch (codeAction) {
                case 1: { //1.  Прочитати базу даних з диску,
                    db_initialized = false;
                    t_initialized = false;

                    String root;
                    do {
                        System.out.println("Введіть шлях до бази: ");
                        try {
                            textLen = System.in.read(readline);
                            root = new String(readline, 0, textLen, "ISO-8859-1");
                            root = root.trim();
                            if(!root.endsWith("/"))
                                root = root + "/";

                            File path = new File(root);
                            File db_file = new File(root + "db.json");
                            if (!path.isDirectory())
                                System.out.println("Шляху " + root +" не існує");
                            else if(!db_file.isFile())
                                System.out.println("За цим шляхом не існує жодної бази");
                            else break;
                        } catch (IOException ex) {
                            System.out.println("Помилка: " + ex.getMessage());
                        }
                    }
                    while(true);

                    try {
                        // root is stated an here
                        db = Database.createFromPath(root);
                        System.out.println("Базу даних \"" + db.name + "\" успішно завантажено.");
                        db.show();
                        db_initialized = true;
                    }
                    catch(IOException | ParseException ex){
                        System.out.println("Помилка: " + ex.getMessage());
                    }

                }
                break;
                case 2: { //2.  Прочитати таблицю з поточної бази даних,
                    if(!db_initialized){
                        System.out.println("Прочитайте базу даних з диску або створіть нову.");
                        break;
                    }

                    String tablename;
                    do {
                        System.out.println("Введіть назву таблиці: ");
                        try {
                            textLen = System.in.read(readline);
                            tablename = new String(readline, 0, textLen, "ISO-8859-1");
                            tablename = tablename.trim();
                            if(!db.doesTableExist(tablename))
                                System.out.println("В базі \"" + db.name + "\" не існує таблиці з назвою \"" + tablename + "\"");
                            else break;
                        } catch (IOException ex) {
                            System.out.println("Помилка: " + ex.getMessage());
                        }
                    }
                    while(true);

                    try {
                        t = db.getTableByName(tablename);
                        System.out.println("Таблицю \"" + t.name + "\" успішно завантажено.");
                        t.show();
                        t_initialized = true;
                    }
                    catch(FileNotFoundException ex){
                        System.out.println("Помилка: " + ex.getMessage());
                    }
                }
                break;
                case 3: { //3.  Створити базу
                    db_initialized = false;
                    t_initialized = false;

                    String root;
                    do {
                        System.out.println("Будь ласка, введіть повний шлях до місця, де буде створена база:");
                        try {
                            textLen = System.in.read(readline);
                            root = new String(readline, 0, textLen, "ISO-8859-1");
                            root = root.trim();
                            if(!root.endsWith("/"))
                                root = root + "/";

                            File dir = new File(root);
                            File db_file = new File(root + "db.json");
                            if(dir.isDirectory() && db_file.exists()){
                                System.out.println("В даній директорії вже існує база; спробуйте інший шлях.");
                            }
                            else {
                                if(!dir.isDirectory())
                                    dir.mkdirs();
                                break;
                            }
                        } catch (IOException ex) {
                            System.out.println("Помилка: " + ex.getMessage());
                        }
                    }
                    while(true);

                    String databaseName;
                    do {
                        System.out.println("Будь ласка, введіть назву бази:");
                        try {
                            textLen = System.in.read(readline);
                            databaseName = new String(readline, 0, textLen, "ISO-8859-1");
                            databaseName = databaseName.trim();
                            break;
                        } catch (IOException ex) {
                            System.out.println("Помилка: " + ex.getMessage());
                        }
                    }
                    while(true);

                    // now root and database name are valid
                    try {
                        db = new Database(databaseName, root);
                        db.save();
                        System.out.println("Нову базу \"" + databaseName + "\" створено!");
                        db_initialized = true;
                    }
                    catch (IOException ex){
                        System.out.println("Помилка: " + ex.getMessage());
                    }
                }
                break;
                case 4: { //4.  Створити таблицю в поточній базі",
                    if(!db_initialized){
                        System.out.println("Прочитайте базу даних з диску або створіть нову.");
                        break;
                    }

                    
                }
                break;
            }
        } while (true);  //глобальний цикл  обробки
    }
}