package com.loppuhajrjoitus.demo;


import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;



@Service
public class FileService {
    File file = new File("students.txt");

    /**
     *  Tallennetaan uusi opiskelija tiedostoon. Funktioon välitetty olio muutetaan JSON tyyliseksi Stringiksi ja tallennetaan tiedostoon.
     * @param s
     */
    public void studentToFile(Students s) {
        try {
            FileWriter fw = new FileWriter(file, true);
            ObjectMapper mapper  = new ObjectMapper();
            String studentJSON = mapper.writeValueAsString(s);
            fw.append(studentJSON + System.lineSeparator());
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Haetaan tiedostosta talleneetut opiskelijat. Tallenetut tiedot muutetaan olioksi ja olioden koostelista palutetaan funktiosta.
     * @return
     */
    public List studentsFromFile() {
        List<Students> students = new ArrayList<>();
        Gson g = new Gson();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String srt = sc.nextLine();
                System.out.println(srt);
                Students s = g.fromJson(srt, Students.class);
                students.add(s);
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return students;
    }

}
