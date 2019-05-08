/*
 * Copyright (c) 2017. http://hiteshsahu.com- All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * If you use or distribute this project then you MUST ADD A COPY OF LICENCE
 * along with the project.
 *  Written by Hitesh Sahu <hiteshkrsahu@Gmail.com>, 2017.
 */

package com.creativematrix.noteapp.util;

import com.creativematrix.noteapp.data.company.Company;

public class CenterRepository {

    private static CenterRepository centerRepository;

    public Company getmCompany() {
        return mCompany;
    }

    public void setmCompany(Company mCompany) {
        this.mCompany = mCompany;
    }

    private Company mCompany;

    //initialize object of list
    public static CenterRepository getCenterRepository() {

        if (null == centerRepository) {
            centerRepository = new CenterRepository();
        }
        return centerRepository;
    }


}
