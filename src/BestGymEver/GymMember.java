package BestGymEver;

import java.time.LocalDate;

public class GymMember {

        private String socialSecurityNr;
        private LocalDate membershipDate;
        private String name;

        public GymMember(String socialSecurityNr, String name, LocalDate membershipDate){
            this.socialSecurityNr = socialSecurityNr;
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
        public String getSocialSecurityNr(){
            return socialSecurityNr;
        }

    }


