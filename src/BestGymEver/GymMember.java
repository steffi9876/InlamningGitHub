package BestGymEver;

import java.time.LocalDate;

public class GymMember {

        private String memberID;
        private LocalDate membershipDate;
        private String name;

        public GymMember(String memberID, String name, LocalDate membershipDate){
            this.memberID = memberID;
            this.membershipDate = membershipDate;
            this.name = name;
        }

        public GymMember() {

        }

        public String getName() {
            return name;
        }

        public boolean isMembershipActive(LocalDate currentDate){
            LocalDate lastyear = currentDate.minusYears(1);
            return membershipDate.isAfter(lastyear);
        }
        public String getMemberID(){
            return memberID;
        }

    }


