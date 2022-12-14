/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sal.dvdlibrary.controller;

import com.sal.dvdlibrary.dao.DvdLibraryDaoException;
import com.sal.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.sal.dvdlibrary.dao.dvdLibraryDao;
import com.sal.dvdlibrary.dto.DvD;
import com.sal.dvdlibrary.ui.DvdLibraryView;
import java.util.List;

/**
 *
 * @author eleon
 */
//Uses Dependency Injection
public class DvdLibraryController {

    // Creating private variables
    private final DvdLibraryView view;
    private final dvdLibraryDao dao;

    public DvdLibraryController(DvdLibraryView view, dvdLibraryDao dao) {
        //Initialize View and Dao
        // ==========================================   IMPLEMENTATION  ================================================

        this.dao = dao;
        this.view = view;
    }

    // Method to run controller
    public void run() {
        boolean keepGoing = true;
        int menuSelection;
        try {
            while(keepGoing){
                menuSelection = getMenuSelection();
                switch (menuSelection) {
                    case 1:
                        createDvd();
                        break;
                    case 2:
                        removeDvd();
                        break;
                    case 3:
                        editDvd();
                        break;
                    case 4:
                        listDvds();
                        break;
                    case 5:
                        viewDvd();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                        break;
                }
            }
        } catch (DvdLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
        exitMessage();
    }

    // Prints the Menu Selection
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    /*
     Method in the Controller to orchestrate the creation of a new student. Our method will do the following:
     Display the Create dvd banner
     Get all the dvd data from the user and create the new dvd object
     Store the new dvd object
    Display the Create dvd Success banner
     */
    // Takes in the New DVD info, which is saved to the DAO
    private void createDvd() throws DvdLibraryDaoException {
      // implement
        // ==========================================   IMPLEMENTATION  ================================================

        view.displayCreateDvDBanner();
        DvD newDvd = view.getNewDvDInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayCreateSuccessBanner();
    }

    /*
     a method called listDvds that will get a list of all Dvd objects in 
    the system from the DAO and then hand that list to the view to display to the user.
     */

    // Gets the DVDs from the DAO and displays them
    private void listDvds() throws DvdLibraryDaoException {
       //implement
        // ==========================================   IMPLEMENTATION  ================================================

        view.displayDisplayAllBanner();
        List<DvD> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }

    /*
    This method asks the view to display the View dvd banner and get the titlr from the user
     */

    // Uses getter to get DVD title from User, then displays the DVD
    private void viewDvd() throws DvdLibraryDaoException {
       //implement
        // ==========================================   IMPLEMENTATION  ================================================

        view.displayDisplayDvdBanner();
        String title = view.getDvdTitleChoice();
        DvD dvd = dao.getDvd(title);
        view.displayDvd(dvd);
    }

    /*
    This method will ask the view to display the Remove dvd banner and ask the user for the title of the dvd to be removed
     */

    // Takes the title from the User then deletes from the DAO
    private void removeDvd() throws DvdLibraryDaoException {
        //implement
        // ==========================================   IMPLEMENTATION  ================================================

        view.displayRemoveDvdBanner();
        String title = view.getDvdTitleChoice();
        DvD removedDvd = dao.removeDvd(title);
        view.displayRemoveResult(removedDvd);
    }

    // Takes the DVD title from the User, gets it from the DAO, and uses a case for options
    private void editDvd() throws DvdLibraryDaoException {
        view.displayEditDvdBanner();
        String title = view.getDvdTitleChoice();
        DvD currentDVD = dao.getDvd(title);

        if (currentDVD == null) {
            view.displayNullDVD();
        } else {
            view.displayDvd(currentDVD);
            int editMenuSelection;
            boolean keepGoing = true;
            while (keepGoing) {
                editMenuSelection = view.printEditMenuAndGetSelection();
                // ==========================================   IMPLEMENTATION  ================================================

                switch (editMenuSelection) {
                    case 1:
                        editReleaseDate(title);
                        break;
                    case 2:
                        editMPAA(title);
                        break;
                    case 3:
                        editDirectorName(title);
                        break;
                    case 4:
                        editUserRating(title);
                        break;
                    case 5:
                        editStudioName(title);
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
                if (!keepGoing) {
                    break;
                }
            }
        }
    }


    private int getEditMenuSelection(){
        return view.printEditMenuAndGetSelection();
    }

    /*
    * Methods for editing the DVD details
    * Displays the associated banners
    * Gets the DVD details from the DAO
    * Edit the details from the DAO
    * Displays the DvD
    * */

    private void editReleaseDate(String title) throws DvdLibraryDaoException {
        view.displayEditReleaseDateBanner();
        String newReleaseDate = view.getNewReleaseDate();
        dao.editReleaseDate(title, newReleaseDate);
        view.displayEditDvdSuccess();
    }

    private void editMPAA(String title) throws DvdLibraryDaoException {
        view.displayEditMpaaBanner();
        String newMpaaRating = view.getNewMpaaRating();
        dao.editMPAA(title, newMpaaRating);
        view.displayEditDvdSuccess();
    }

    private void editDirectorName(String title) throws DvdLibraryDaoException {
        view.displayEditDirectorNameBanner();
        String newDirectorName = view.getNewDirectorName();
        dao.editDirectorName(title, newDirectorName);
        view.displayEditDvdSuccess();
    }

    private void editUserRating(String title) throws DvdLibraryDaoException {
        view.displayEditUserRating();
        String newUserRating = view.getNewUserRating();
        dao.editUserRating(title, newUserRating);
        view.displayEditDvdSuccess();
    }

    private void editStudioName(String title) throws DvdLibraryDaoException {
        view.displayEditStudio();
        String newStudioName = view.getNewStudio();
        dao.editStudio(title, newStudioName);
        view.displayEditDvdSuccess();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
    
}
