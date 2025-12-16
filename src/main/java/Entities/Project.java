package Entities;

import java.util.ArrayList;

public class Project {
    private String name;
    private String description;
    private ArrayList<DefaultActivity> activities;


    public Project(String name, String description, ArrayList<DefaultActivity> activities) {
        this.name = name;
        this.description = description;
        if(activities == null) {
            this.activities = new ArrayList<DefaultActivity>();
        }
        this.activities = activities;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<DefaultActivity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<DefaultActivity> activities) {
        this.activities = activities;
    }
}
