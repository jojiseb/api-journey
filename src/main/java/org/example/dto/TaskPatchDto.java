package org.example.dto;

public class TaskPatchDto {
    private String title;
    private Boolean completed; //In Patch request, the field missing gets updated to default value if using boolean.
    // So, Better use wrapper classes

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public Boolean isCompleted() {
        return completed;
    }
}
