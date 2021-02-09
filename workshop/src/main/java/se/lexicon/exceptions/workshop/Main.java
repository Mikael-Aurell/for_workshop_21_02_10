package se.lexicon.exceptions.workshop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import se.lexicon.exceptions.workshop.data_access.NameService;
import se.lexicon.exceptions.workshop.domain.Person;
import se.lexicon.exceptions.workshop.fileIO.CSVReader_Writer;

public class Main {

	public static void main(String[] args) {

	    List <String> maleFirstNames = CSVReader_Writer.getMaleFirstNames();

	    List <String> femaleFirstNames = CSVReader_Writer.getFemaleFirstNames();

        List <String> lastNames = null;

        try {
            lastNames = CSVReader_Writer.getLastNames();
        } catch (IOException e) {
            e.printStackTrace();
        }

        NameService nameService = new NameService(maleFirstNames, femaleFirstNames,lastNames);

        Person test = nameService.getNewRandomPerson();
        List<Person> addPersonToList = new ArrayList<>();
        addPersonToList.add(test);

        String message = CSVReader_Writer.writePersonToFile(test);

        System.out.println(test);
        System.out.println(message);

        try {
            nameService.addMaleFirstName("Liam");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
