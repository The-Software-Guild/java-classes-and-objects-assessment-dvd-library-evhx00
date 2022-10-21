/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sal.dvdlibrary.ui;

import com.sal.dvdlibrary.dto.DvD;
import java.util.List;

/**
 *
 * @author eleon
 */
public class DvdLibraryView {


    private final UserIO io;

    // Used for User Interface
    public DvdLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
       //implement
        // prints menu
        // ==========================================   IMPLEMENTATION  ================================================
        io.print("Main Menu");
        io.print("1. Add a DVD to the collection");
        io.print("2. Remove a DVD from the collection");
        io.print("3. Edit information for an existing DVD in the collection");
        io.print("4. List DVD's");
        io.print("5. Search DVD by title");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 8);
    }
    public int printEditMenuAndGetSelection() {
      //implement
        // ==========================================   IMPLEMENTATION  ================================================
        // prints menu

        io.print("Edit DVD Menu");
        io.print("1. Edit title");
        io.print("2. Edit release date");
        io.print("3. Edit MPAA rating");
        io.print("4. Edit director's name");
        io.print("5. Edit user rating");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 8);
    }
    /*
     This method prompts the user for dvd ID, First Name, Last Name, and Cohort, 
    gathers this information, creates a new dvd object, and returns it to the caller.
     */
    public DvD getNewDvDInfo() {
     //implement
        // ==========================================   IMPLEMENTATION  ================================================

        String title = io.readString("Please enter DVD tile");
        String releaseDate = io.readString("Please enter DVD release Date");
        String MPAA = io.readString("Please enter MPAA rating");
        String directorsName = io.readString("Please enter Directors full name");
        String studio = io.readString("Please enter Studio name");
        String userRating = io.readString("Please enter user rating");

        DvD currentDVD = new DvD(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMPAA(MPAA);
        currentDVD.setDirectorsName(directorsName);
        currentDVD.setStudio(studio);
        currentDVD.setUserRating(userRating);
        return currentDVD;
    }

    /*
    This method simply displays a banner or heading to the UI indicating that 
    the next interactions on the screen will be for creating a new dvd
     */
    public void displayCreateDvDBanner() {
        io.print("=== Create DvD ===");
    }

    /*
    The second method displays a message that the new dvd was successfully created
    and waits for the user to hit Enter to continue
     */
    public void displayCreateSuccessBanner() {
        io.readString(
                "DvD successfully created.  Please hit enter to continue");
    }

    /*
    A method that takes a list of DVD objects as a parameter and displays the information for each Dvd to the screen.
     */
    public void displayDvdList(List<DvD> dvdList) {
     //implement
        // ==========================================   IMPLEMENTATION  ================================================
        // Formats into string, then prints
        for (DvD currentDvd : dvdList) {
            String DvdInfo = String.format("#%s : %s %s %s %s %s",
                    currentDvd.getTitle(),
                    currentDvd.getReleaseDate(),
                    currentDvd.getMPAA(),
                    currentDvd.getDirectorsName(),
                    currentDvd.getStudio(),
                    currentDvd.getUserRating());
            io.print(DvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All Dvds ===");
    }

    /*
    Shows the student banner
     */
    public void displayDisplayDvdBanner() {
        io.print("=== Display Dvd ===");
    }

    /*
    Get the dvd title to display information
     */
    public String getDvdTitleChoice() {
        return io.readString("Please enter the dvd title.");
    }

    /*
    Displays the dvd information
     */
    public void displayDvd(DvD dvd) {
        //implement
        // ==========================================   IMPLEMENTATION  ================================================
        // Displays DVD info using getters
        if (dvd != null) {
            io.print(dvd.getTitle());
            io.print(dvd.getReleaseDate());
            io.print(dvd.getMPAA());
            io.print(dvd.getDirectorsName());
            io.print(dvd.getStudio());
            io.print(dvd.getUserRating());
            io.print("");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveDvdBanner() {
        io.print("=== Remove Dvd ===");
    }

    public void displayRemoveResult(DvD dvdRecord) {
       //implement
        // ==========================================   IMPLEMENTATION  ================================================
        // Uses reasString to display the DVD information
        if(dvdRecord != null){
            io.print("DVD successfully removed.");
        }else{
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    public void displayEditDvdBanner() {
        io.print("=== Edit DVD ===");
    }

    public void displayEditDvdSuccess() {
        io.readString(
                "DVD successfully Edited.  Please hit enter to continue");
    }

    public void displayEditReleaseDateBanner() {
        io.print("=== Edit DVD Release Date ===");
    }

    public void displayEditMpaaBanner() {
        io.print("=== Edit DVD MPAA rating ===");
    }

    public void displayEditDirectorNameBanner() {
        io.print("=== Edit DVD Director's Name ===");
    }

    public void displayEditStudio() {
        io.print("=== Edit DVD Studio ===");
    }

    public void displayEditUserRating() {
        io.print("=== Edit DVD User Rating ===");
    }


    // Getters
    public String getNewReleaseDate() {
        return io.readString("Please enter new release date.");
    }

    public String getNewMpaaRating() {
        return io.readString("Please enter new MPAA rating.");
    }

    public String getNewDirectorName() {
        return io.readString("Please enter new director's name.");
    }

    public String getNewUserRating() {
        return io.readString("Please enter new user rating.");
    }

    public String getNewStudio() {
        return io.readString("Please enter new studio.");
    }

    public void displayNullDVD() {
        io.print("No such DVD.");
        io.readString("Please hit enter to continue.");
    }
}
