package BestGymEver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

    public class BestGym extends GymMember{

        public BestGym() throws IOException {
            super();
        }

        Path membersPath = Paths.get("src/BestGymEver/GymMembers.txt");
        Path ptTracking = Paths.get("src/BestGymEver/ptTracking.txt");
        boolean approved = false; // Denna håller koll på om namn eller personnr existerar i membersPath


        // Text som förekommer i metoden
        String question = "Write name och social security number to check: ";
        String active = "Active membership";
        String expired = "Expired membership";
        String writtenToPt = "Visit has been written to PT-tracking file";
        String notApproved = "This person has never been a member";



        public void readAndFindMembersAndWriteToFile(){
            LocalDate currentDate = LocalDate.now();
            Scanner userInput = new Scanner(System.in);
            System.out.println(question);
            String search = userInput.nextLine();

            try(BufferedReader reader = Files.newBufferedReader(membersPath);
                BufferedWriter ptWriter = Files.newBufferedWriter(ptTracking, StandardOpenOption.APPEND)){
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    String socialsecurityNr = parts[0].trim();
                    String name = parts[1].trim();
                    String date = reader.readLine().trim();
                    LocalDate paymentDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                    GymMember member = new GymMember(socialsecurityNr, name, paymentDate);

                    if (member.getMemberID().equals(search) || member.getName().equals(search)) {
                        if (member.isMembershipActive(currentDate)) {
                            approved = true;
                            System.out.println(active);

                            String visitDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            String memberInfo = visitDate + ", " + socialsecurityNr + ", " + name;

                            ptWriter.write(memberInfo);
                            ptWriter.newLine();
                            ptWriter.flush();
                            System.out.println(writtenToPt);

                        } else {
                            approved = true; // Finns i filen, men utgånget medlemskap
                            System.out.println(expired);
                        }
                        break;
                    }
                }
                if (!approved) { // Finns inte i filen!
                    System.out.println(notApproved);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


