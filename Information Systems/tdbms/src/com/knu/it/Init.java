package com.knu.it;

import com.knu.it.db.Database;
import com.knu.it.db.Table;
import com.knu.it.db.TableColumn;
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
        String[] menu = {"1.  Прочитати базу даних з диску",
                         "2.  Прочитати таблицю з поточної бази даних",
                         "3.  Створити базу",
                         "4.  Створити таблицю в поточній базі",
                         "5.  Видалити таблицю з поточної бази",
                         "6.  Проекція поточної таблиці",
                         "7.  Додати до поточної таблиці новий рядок",
                         "8.  Редагувати поточну таблицю в базі"
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
                case 1: { //1.  Прочитати базу даних з диску
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
                case 2: { //2.  Прочитати таблицю з поточної бази даних
                    t_initialized = false;

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
                case 4: { //4.  Створити таблицю в поточній базі
                    t_initialized = false;

                    if(!db_initialized){
                        System.out.println("Прочитайте базу даних з диску або створіть нову.");
                        break;
                    }

                    String tableName;
                    do {
                        System.out.println("Будь ласка, введіть назву таблиці:");
                        try {
                            textLen = System.in.read(readline);
                            tableName = new String(readline, 0, textLen, "ISO-8859-1");
                            tableName = tableName.trim();
                            break;
                        } catch (IOException ex) {
                            System.out.println("Помилка: " + ex.getMessage());
                        }
                    }
                    while(true);

                    int columns_count;
                    do {
                        System.out.println("Будь ласка, введіть кількість стовпчиків:");
                        try {
                            textLen = System.in.read(readline);
                            String str = new String(readline, 0, textLen, "ISO-8859-1");
                            str = str.trim();
                            columns_count = new Integer(str);
                            break;
                        } catch (IOException | NumberFormatException ex) {
                            System.out.println("Помилка: " + ex.getMessage());
                        }
                    }
                    while(true);

                    List<TableColumn> columns = new ArrayList<>();
                    for(int i = 0; i < columns_count; i++){
                        do {
                            System.out.println("Будь ласка, введіть назву стовпчика " + (i+1) + "/" +  columns_count + " і його тип через кому (один з {html, int, long, char, double}):");
                            try {
                                textLen = System.in.read(readline);
                                String str = new String(readline, 0, textLen, "ISO-8859-1");
                                str = str.trim();
                                String[] data = str.split(",");
                                TableColumn newCol = new TableColumn(data[0].trim(), Constants.GetClass(data[1].trim()));
                                columns.add(newCol);
                                break;
                            } catch (IOException | IllegalArgumentException ex) {
                                System.out.println("Помилка: " + ex.getMessage());
                            }
                        }
                        while(true);
                    }

                    try {
                        t = new Table(tableName, db.root, tableName + ".json", columns, new JSONArray());
                        db.tables.add(t);
                        t.save();
                        db.save();
                        System.out.println("Нову таблицю \"" + tableName + "\" створено!");
                        t_initialized = true;
                    }
                    catch(IOException ex){
                        System.out.println("Помилка: " + ex.getMessage());
                    }
                }
                break;
                case 5: { //5.  Видалити таблицю з поточної бази
                    if(!db_initialized){
                        System.out.println("Прочитайте базу даних з диску або створіть нову.");
                        break;
                    }

                    String tableName;
                    do {
                        System.out.println("Будь ласка, введіть назву таблиці, яку хочете видалити:");
                        try {
                            textLen = System.in.read(readline);
                            tableName = new String(readline, 0, textLen, "ISO-8859-1");
                            tableName = tableName.trim();

                            t = db.getTableByName(tableName);
                            break;
                        } catch (IOException ex) {
                            System.out.println("Помилка: " + ex.getMessage());
                        }
                    }
                    while(true);

                    try{
                        db.tables.remove(t);
                        db.save();

                        File tableFile = new File(t.root + t.path);
                        tableFile.delete();

                        System.out.println("Таблицю \"" + tableName + "\" видалено!");
                    }
                    catch(IOException ex){
                        System.out.println("Помилка: " + ex.getMessage());
                    }
                }
                break;
                case 6: { //6.  Проекція поточної таблиці
                    if(!db_initialized){
                        System.out.println("Прочитайте базу даних з диску або створіть нову.");
                        break;
                    }

                    if(!t_initialized){
                        System.out.println("Прочитайте таблицю з диску або створіть нову.");
                        break;
                    }

                    int columns_count;
                    do {
                        System.out.println("Будь ласка, введіть кількість стовпчиків в проекції:");
                        try {
                            textLen = System.in.read(readline);
                            String str = new String(readline, 0, textLen, "ISO-8859-1");
                            str = str.trim();
                            columns_count = new Integer(str);
                            break;
                        } catch (IOException | NumberFormatException ex) {
                            System.out.println("Помилка: " + ex.getMessage());
                        }
                    }
                    while(true);

                    List<String> columns = new ArrayList<>();
                    for(int i = 0; i < columns_count; i++){
                        do {
                            System.out.println("Будь ласка, введіть назву стовпчика " + (i+1) + "/" +  columns_count + " в проекції:");
                            try {
                                textLen = System.in.read(readline);
                                String str = new String(readline, 0, textLen, "ISO-8859-1");
                                str = str.trim();
                                columns.add(str);
                                break;
                            } catch (IOException | IllegalArgumentException ex) {
                                System.out.println("Помилка: " + ex.getMessage());
                            }
                        }
                        while(true);
                    }

                    Table projectedTable = t.project(columns);
                    System.out.println("Таблицю успішно спроектовано.");
                    projectedTable.show();
                }
                break;
                case 7: { //7.  Додати до поточної таблиці новий рядок
                    if(!db_initialized){
                        System.out.println("Прочитайте базу даних з диску або створіть нову.");
                        break;
                    }

                    if(!t_initialized){
                        System.out.println("Прочитайте таблицю з диску або створіть нову.");
                        break;
                    }

                    JSONObject row = new JSONObject();
                    for(TableColumn tc: t.columns){
                        do {
                            System.out.println("Будь ласка, введіть поле \"" + tc.name + "\", тип поля \""+ Constants.GetClassName(tc.type) +"\":");
                            try {
                                textLen = System.in.read(readline);
                                String str = new String(readline, 0, textLen, "ISO-8859-1");
                                str = str.trim();

                                Object o;
                                if(tc.type == HTML.class) {
                                    o = str;
                                    new HTML(t.root, str).validate();
                                }
                                else if (tc.type == Integer.class){
                                    o = new Integer(str);
                                }
                                else if (tc.type == Long.class){
                                    o = new Long(str);
                                }
                                else if (tc.type == Character.class){
                                    if(str.length()!= 1)
                                        throw new IllegalArgumentException("Please enter exactly 1 symbol.");
                                    o = str.substring(0, 1);
                                }
                                else if (tc.type == Double.class){
                                    o = new Double(str);
                                }
                                else
                                    o = null;
                                row.put(tc.name, o);
                                break;
                            } catch (IOException | IllegalArgumentException ex) {
                                System.out.println("Помилка: " + ex.getMessage());
                            }
                        }
                        while(true);
                    }

                    try{
                        t.fields.add(row);
                        t.save();
                        System.out.println("Новий рядок додано.");
                        t.show();
                    }
                    catch(IOException ex){
                        System.out.println("Помилка: " + ex.getMessage());
                    }
                }
                break;
                case 8:{ //8.  Редагувати поточну таблицю в базі
                    if(!db_initialized){
                        System.out.println("Прочитайте базу даних з диску або створіть нову.");
                        break;
                    }

                    if(!t_initialized){
                        System.out.println("Прочитайте таблицю з диску або створіть нову.");
                        break;
                    }

                    int row_num = 0;
                    TableColumn col = null;
                    do {
                        System.out.println("Будь ласка, введіть через кому номер рядка і назву стовпчика, який Ви хочете редагувати:");
                        try {
                            textLen = System.in.read(readline);
                            String str = new String(readline, 0, textLen, "ISO-8859-1");
                            str = str.trim();
                            String[] data = str.split(",");
                            row_num = new Integer(data[0].trim());
                            String col_name = data[1].trim();

                            boolean col_found = false;
                            for(TableColumn tc: t.columns){
                                if(tc.name.equals(col_name)){
                                    col = tc;
                                    col_found = true;
                                }
                            }
                            if(col_found)
                                break;
                            else
                                throw new IllegalArgumentException("Стовпчика з назвою \"" + col_name + "\" не існує");
                        } catch (IOException | IllegalArgumentException ex) {
                            System.out.println("Помилка: " + ex.getMessage());
                        }
                    }
                    while(true);


                    Object value;
                    do {
                        System.out.println("Будь ласка, введіть нове значення поля типу \"" + Constants.GetClassName(col.type) + "\":");
                        try {
                            textLen = System.in.read(readline);
                            String str = new String(readline, 0, textLen, "ISO-8859-1");
                            str = str.trim();
                            if(col.type == HTML.class) {
                                value = str;
                                new HTML(t.root, str).validate();
                            }
                            else if (col.type == Integer.class){
                                value = new Integer(str);
                            }
                            else if (col.type == Long.class){
                                value = new Long(str);
                            }
                            else if (col.type == Character.class){
                                if(str.length()!= 1)
                                    throw new IllegalArgumentException("Please enter exactly 1 symbol.");
                                value = str.substring(0, 1);
                            }
                            else if (col.type == Double.class){
                                value = new Double(str);
                            }
                            else
                                value = null;
                            break;
                        } catch (IOException | IllegalArgumentException ex) {
                            System.out.println("Помилка: " + ex.getMessage());
                        }
                    }
                    while(true);

                    try {
                        t.update(row_num, col, value);
                        t.save();
                        System.out.println("Таблицю успішно оновлено!");
                        t.show();
                    }
                    catch(IOException ex){
                        System.out.println("Помилка: " + ex.getMessage());
                    }
                }
                break;
            }
        } while (true);
    }
}