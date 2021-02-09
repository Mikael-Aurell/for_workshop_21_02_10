package se.lexicon.exceptions.workshop.fileIO;

import se.lexicon.exceptions.workshop.domain.Person;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReader_Writer {
	 /**
     * This method getMaleFirstNames should use a try-catch-finally without resources
     * Should catch FileNotFoundException and IOException
     * You should also close the Buffered reader in the finally block
     * @return List<String>of male firstnames
     */
    public static List<String> getMaleFirstNames() {

        BufferedReader reader = null;
        List<String> names = null;

        try {
            reader = Files.newBufferedReader(Paths.get("workshop/firstname_males.txt"));

            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return names;
        }
    }

    /**
     * This method getFemaleFirstNames should make use of a try-catch with resources
     * @return
     */
    public static List<String> getFemaleFirstNames(){

        List<String> names=null;

        BufferedReader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("workshop/firstname_female.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        names = reader.lines()
                        .flatMap(line -> Stream.of(line.split(",")))
                        .collect(Collectors.toList());
        return names;
    }

    /**
     * This method fetches strings from a file and put them into a list
     * This method might throw IOException which due to the throws clause need to
     * be handled by the caller.
     * @return List <String> of last names
     * @throws IOException
     */
    public static List<String> getLastNames() throws IOException{

        List<String> names = null;
        BufferedReader reader = null;

        try{
                reader = Files.newBufferedReader(Paths.get("workshop/lastnames.txt"));
                names = reader.lines()
                .flatMap(line -> Stream.of(line.split(",")))
                .collect(Collectors.toList());
        }finally{
            if(reader != null){
                reader.close();
            }
        }
        return names;
    }


    public static void saveLastNames(List <String> lastNames){

        BufferedWriter writer = null;
        try {
            writer = Files.newBufferedWriter(Paths.get("lastnames.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String toWrite : lastNames){
            try {
                writer.append(toWrite+",");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveFemaleNames(List <String> femaleNames){
        BufferedWriter writer = null;
        try {
            writer = Files.newBufferedWriter(Paths.get("firstname_female.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String toWrite : femaleNames){
            try {
                writer.append(toWrite+",");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveMaleNames(List <String> maleNames){
        BufferedWriter writer = null;
        try {
            writer = Files.newBufferedWriter(Paths.get("firstname_males.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String toWrite : maleNames){
            try {
                writer.append(toWrite+",");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String writePersonToFile(Person person) {
        FileSystem fileSystem = FileSystems.getDefault();
        Path path = fileSystem.getPath("workshop/testPersons.txt");
        if (person == null) return "No input.";
        String personContent = ("\n" + person.getFirstName() + " " + person.getLastName());
        try {
            Files.write(path, personContent.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "The person was successfully added to the txt file.";
    }
}
