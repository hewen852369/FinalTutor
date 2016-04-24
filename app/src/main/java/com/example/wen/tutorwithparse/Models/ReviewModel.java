package com.example.wen.tutorwithparse.Models;

        import java.io.Serializable;

        /**
  * Created by Priyanka on 4/20/2016.
  */
        public class ReviewModel implements Serializable {
        private Tutor tutor;
        private String name;
        private String comment;
        private int rating;

                public ReviewModel(String name, String comment, int rating) {
                this.name = name;
                this.comment = comment;
                this.rating = rating;
            }

                public Tutor getTutor() {
                return tutor;
            }

                public String getComment(){return comment;}

                public int getRating(){return rating;};

                public String getName(){return name;}

                public void setComment(String message) {
                this.comment = comment;
            }

                public void setRating(int rating) {
                this.rating = rating;
            }

                public void setName(String name) {
                this.name = name;
            }

                public void setTutor(Tutor tutor) {
                this.tutor = tutor;
            }
    }
