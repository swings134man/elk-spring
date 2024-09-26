package com.lucas.bootelastic4.common.utils;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV Parse To List<Object>
 *     Using For ElasticSearch Data Import
 *
 */
@Service
public class CsvParser {

    public List<Object> parseToList(String fileName, Object targetEntity) throws InstantiationException, IllegalAccessException {
        // 1. Target Entity Class, Fields Get
        List<Object> targetList = new ArrayList<>();
        Class<?> aClass = targetEntity.getClass();

        // 2. Read CSV File
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("static/csv/"+ fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            CSVReader csvReader = new CSVReader(inputStreamReader);
            String[] headers = csvReader.readNext();
            String[] row;

            while((row = csvReader.readNext()) != null) {
                Object object = aClass.newInstance(); // Entity Instance
                Field[] declaredFields = object.getClass().getDeclaredFields();

                // field Type 에 따라 data Set
                for(Field field: declaredFields){
                    field.setAccessible(true);

                    // Mapping With CSV Header
                    for(int i = 0; i < headers.length; i++) {
                        if(field.getName().equalsIgnoreCase(headers[i])){
                            if(field.getType() == Long.class || field.getType() == long.class) {
                                field.set(object, Long.parseLong(row[i]));
                            } else if(field.getType() == String.class) {
                                field.set(object, row[i]);
                            } else if(field.getType() == int.class || field.getType() == Integer.class) {
                                field.set(object, Integer.parseInt(row[i]));
                            } else if (field.getType() == float.class || field.getType() == Float.class) {
                                field.set(object, Float.parseFloat(row[i]));
                            } else if(field.getType() == double.class || field.getType() == Double.class) {
                                field.set(object, Double.parseDouble(row[i]));
                            }
                        }
                    }
                }
                targetList.add(object);
            }

            csvReader.close();
            inputStreamReader.close();
            inputStream.close();

            return targetList;
        }catch (Exception e) {
            e.printStackTrace();
        }
        // 3. CSV 파일을 읽어서 엔티티 클래스에 매핑
        // 4. 엔티티 클래스를 리스트에 담아서 리턴
        return targetList;
    }
}
