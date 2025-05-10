package com.udacity.jdnd.course3.critter;


import static org.assertj.core.api.Assertions.*;

import com.udacity.jdnd.course3.critter.controller.*;
import com.udacity.jdnd.course3.critter.dto.*;
import java.time.*;
import java.util.*;
import java.util.stream.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.transaction.annotation.*;


/**
 * This is a set of functional tests to validate the basic capabilities desired for this
 * application. Students will need to configure the application to run these tests by adding
 * application.properties file to the test/resources directory that specifies the datasource. It can
 * run using an in-memory H2 instance and should not try to re-use the same datasource used by the
 * rest of the app.
 * <p>
 * These tests should all pass once the project is complete.
 */
@SpringBootTest(classes = CritterApplication.class)
@Transactional
class CritterFunctionalTest {

    private final UserController userController;
    private final PetController petController;
    private final ScheduleController scheduleController;

    @Autowired
    public CritterFunctionalTest(UserController userController, PetController petController,
            ScheduleController scheduleController) {
        this.userController = userController;
        this.petController = petController;
        this.scheduleController = scheduleController;
    }

    @Test
    void testCreateCustomer() {
        CustomerDTO customerDTO = createCustomerDTO();
        CustomerDTO newCustomer = userController.saveCustomer(customerDTO);
        CustomerDTO retrievedCustomer = userController.getAllCustomers().getFirst();

        assertThat(newCustomer.getName()).isEqualTo(customerDTO.getName());
        assertThat(newCustomer.getId()).isEqualTo(retrievedCustomer.getId());
        assertThat(retrievedCustomer.getPetIds()).isInstanceOf(List.class);
    }

    @Test
    void testCreateEmployee() {
        EmployeeDTO employeeDTO = createEmployeeDTO();
        EmployeeDTO newEmployee = userController.saveEmployee(employeeDTO);
        EmployeeDTO retrievedEmployee = userController.getEmployee(newEmployee.getId());

        assertThat(newEmployee.getName()).isEqualTo(employeeDTO.getName());
        assertThat(employeeDTO.getSkills()).containsExactlyElementsOf(newEmployee.getSkills());
        assertThat(newEmployee.getDaysAvailable()).isEqualTo(employeeDTO.getDaysAvailable());
        assertThat(newEmployee.getId()).isEqualTo(retrievedEmployee.getId());
        assertThat(retrievedEmployee.getId()).isInstanceOf(UUID.class);
    }

    @Test
    void testAddPetsToCustomer() {
        CustomerDTO customerDTO = createCustomerDTO();
        CustomerDTO newCustomer = userController.saveCustomer(customerDTO);

        PetDTO petDTO = createPetDTO();
        petDTO.setOwnerId(newCustomer.getId());
        PetDTO newPet = petController.savePet(petDTO);

        //make sure pet contains customer id
        PetDTO retrievedPet = petController.getPet(newPet.getId());
        Assertions.assertEquals(retrievedPet.getId(), newPet.getId());
        Assertions.assertEquals(retrievedPet.getOwnerId(), newCustomer.getId());

        //make sure you can retrieve pets by owner
        List<PetDTO> pets = petController.getPetsByOwner(newCustomer.getId());
        Assertions.assertEquals(newPet.getId(), pets.getFirst()
                                                    .getId());
        Assertions.assertEquals(newPet.getName(), pets.getFirst()
                                                      .getName());

        //check to make sure customer now also contains pet
        CustomerDTO retrievedCustomer = userController.getAllCustomers()
                                                      .getFirst();
        Assertions.assertTrue(
                retrievedCustomer.getPetIds() != null && !retrievedCustomer.getPetIds()
                                                                           .isEmpty());
        Assertions.assertEquals(retrievedCustomer.getPetIds()
                                                 .getFirst(), retrievedPet.getId());
    }

    @Test
    void testFindPetsByOwner() {
        CustomerDTO customerDTO = createCustomerDTO();
        CustomerDTO newCustomer = userController.saveCustomer(customerDTO);

        PetDTO petDTO = createPetDTO()
                .setOwnerId(newCustomer.getId());
        PetDTO newPet = petController.savePet(petDTO);

        petDTO.setType(2L);
        petDTO.setName("DogName");
        PetDTO newPet2 = petController.savePet(petDTO);

        List<PetDTO> pets = petController.getPetsByOwner(newCustomer.getId());
        assertThat(pets).hasSize(2);

        assertThat(pets.getFirst().getOwnerId()).isEqualTo(newCustomer.getId());
        assertThat(pets.getFirst().getId()).isEqualTo(newPet.getId());

        assertThat(pets.get(1).getOwnerId()).isEqualTo(newCustomer.getId());
        assertThat(pets.get(1).getId()).isEqualTo(newPet2.getId());
    }

    @Test
    void testFindOwnerByPet() {
        CustomerDTO customerDTO = createCustomerDTO();
        CustomerDTO newCustomer = userController.saveCustomer(customerDTO);

        PetDTO petDTO = createPetDTO()
                .setOwnerId(newCustomer.getId());
        PetDTO newPet = petController.savePet(petDTO);

        CustomerDTO owner = userController.getOwnerByPet(newPet.getId());
        Assertions.assertEquals(owner.getId(), newCustomer.getId());
        Assertions.assertEquals(owner.getPetIds().getFirst(), newPet.getId());
    }

    @Test
    void testChangeEmployeeAvailability() {
        EmployeeDTO employeeDTO = createEmployeeDTO();
        EmployeeDTO emp1 = userController.saveEmployee(employeeDTO);
        Assertions.assertNull(emp1.getDaysAvailable());

        Set<DayOfWeek> availability = Set.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
                DayOfWeek.WEDNESDAY);
        userController.setAvailability(emp1.getId(), availability);

        EmployeeDTO emp2 = userController.getEmployee(emp1.getId());
        Assertions.assertEquals(availability, emp2.getDaysAvailable());
    }

    @Test
    void testFindEmployeesByServiceAndTime() {
        EmployeeDTO emp1 = createEmployeeDTO();
        EmployeeDTO emp2 = createEmployeeDTO();
        EmployeeDTO emp3 = createEmployeeDTO();

        emp1.setDaysAvailable(Set.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY));
        emp2.setDaysAvailable(Set.of(DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));
        emp3.setDaysAvailable(Set.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));

        emp1.setSkills(Set.of(3L, 1L));
        emp2.setSkills(Set.of(1L, 2L));
        emp3.setSkills(Set.of(2L, 5L));

        EmployeeDTO emp1n = userController.saveEmployee(emp1);
        EmployeeDTO emp2n = userController.saveEmployee(emp2);
        EmployeeDTO emp3n = userController.saveEmployee(emp3);

        //make a request that matches employee 1 or 2
        EmployeeRequestDTO er1 = new EmployeeRequestDTO();
        er1.setDate(LocalDate.of(2019, 12, 25)); //wednesday
        er1.setSkills(Set.of(1L));

        Set<UUID> eIds1 = userController.findEmployeesForService(er1)
                                        .stream()
                                        .map(EmployeeDTO::getId)
                                        .collect(Collectors.toSet());
        Set<UUID> eIds1expected = Set.of(emp1n.getId(), emp2n.getId());
        Assertions.assertEquals(eIds1expected, eIds1);

        //make a request that matches only employee 3
        EmployeeRequestDTO er2 = new EmployeeRequestDTO();
        er2.setDate(LocalDate.of(2019, 12, 27)); //friday
        er2.setSkills(Set.of(2L, 5L));

        Set<UUID> eIds2 = userController.findEmployeesForService(er2)
                                        .stream()
                                        .map(EmployeeDTO::getId)
                                        .collect(
                                                Collectors.toSet());
        Set<UUID> eIds2expected = Set.of(emp3n.getId());
        Assertions.assertEquals(eIds2expected, eIds2);
    }

    @Test
    void testSchedulePetsForServiceWithEmployee() {
        EmployeeDTO employeeTemp = createEmployeeDTO();
        employeeTemp.setDaysAvailable(
                Set.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY));
        EmployeeDTO employeeDTO = userController.saveEmployee(employeeTemp);
        CustomerDTO customerDTO = userController.saveCustomer(createCustomerDTO());
        PetDTO petTemp = createPetDTO();
        petTemp.setOwnerId(customerDTO.getId());
        PetDTO petDTO = petController.savePet(petTemp);

        LocalDate date = LocalDate.of(2019, 12, 25);
        List<UUID> petList = List.of(petDTO.getId());
        List<UUID> employeeList = List.of(employeeDTO.getId());
        Set<Long> skillSet = Set.of(1L);

        scheduleController.createSchedule(createScheduleDTO(petList, employeeList, date, skillSet));
        ScheduleDTO scheduleDTO = scheduleController.getAllSchedules().getFirst();

        assertThat(scheduleDTO.getActivities()).isEqualTo(skillSet);
        assertThat(scheduleDTO.getDate()).isEqualTo(date);
        assertThat(scheduleDTO.getEmployeeIds()).isEqualTo(employeeList);
        assertThat(scheduleDTO.getPetIds()).isEqualTo(petList);
    }

    @Test
    void testFindScheduleByEntities() {
        ScheduleDTO sched1 = populateSchedule(1, 2, LocalDate.of(2019, 12, 25),
                Set.of(3L, 2L));
        ScheduleDTO sched2 = populateSchedule(3, 1, LocalDate.of(2019, 12, 26),
                Set.of(1L));

        //add a third schedule that shares some employees and pets with the other schedules
        ScheduleDTO sched3 = new ScheduleDTO()
                .setEmployeeIds(sched1.getEmployeeIds())
                .setPetIds(sched2.getPetIds())
                .setActivities(Set.of(5L, 1L))
                .setDate(LocalDate.of(2020, 3, 23));
        scheduleController.createSchedule(sched3);

        /*
            We now have 3 schedule entries. The third schedule entry has the same employees as the 1st schedule
            and the same pets/owners as the second schedule. So if we look up schedule entries for the employee from
            schedule 1, we should get both the first and third schedule as our result.
         */

        //Employee 1 in is both schedule 1 and 3
        List<ScheduleDTO> scheds1e = scheduleController.getScheduleForEmployee(
                sched1.getEmployeeIds()
                      .getFirst());
        compareSchedules(sched1, scheds1e.getFirst());
        compareSchedules(sched3, scheds1e.get(1));

        //Employee 2 is only in schedule 2
        List<ScheduleDTO> scheds2e = scheduleController.getScheduleForEmployee(
                sched2.getEmployeeIds()
                      .getFirst());
        compareSchedules(sched2, scheds2e.getFirst());

        //Pet 1 is only in schedule 1
        List<ScheduleDTO> scheds1p = scheduleController.getScheduleForPet(sched1.getPetIds()
                                                                                .getFirst());
        compareSchedules(sched1, scheds1p.getFirst());

        //Pet from schedule 2 is in both schedules 2 and 3
        List<ScheduleDTO> scheds2p = scheduleController.getScheduleForPet(sched2.getPetIds()
                                                                                .getFirst());
        compareSchedules(sched2, scheds2p.getFirst());
        compareSchedules(sched3, scheds2p.get(1));

        //Owner of the first pet will only be in schedule 1
        List<ScheduleDTO> scheds1c = scheduleController.getScheduleForCustomer(
                userController.getOwnerByPet(sched1.getPetIds()
                                                   .getFirst())
                              .getId());
        compareSchedules(sched1, scheds1c.getFirst());

        //Owner of pet from schedule 2 will be in both schedules 2 and 3
        List<ScheduleDTO> scheds2c = scheduleController.getScheduleForCustomer(
                userController.getOwnerByPet(sched2.getPetIds()
                                                   .getFirst())
                              .getId());
        compareSchedules(sched2, scheds2c.getFirst());
        compareSchedules(sched3, scheds2c.get(1));
    }

    private static EmployeeDTO createEmployeeDTO() {
        return new EmployeeDTO()
                .setName("TestEmployee")
                .setSkills(Set.of(3L, 1L));
    }

    private static CustomerDTO createCustomerDTO() {
        return new CustomerDTO()
                .setName("TestEmployee")
                .setPhoneNumber("123-456-789");
    }

    private static PetDTO createPetDTO() {
        return new PetDTO()
                .setName("TestPet")
                .setType(1L) // The Type ID (1L) is from the SQL setup for testing
                .setBirthDate(LocalDate.of(2017, 12, 25));
    }

    private static EmployeeRequestDTO createEmployeeRequestDTO() {
        return new EmployeeRequestDTO()
                .setDate(LocalDate.of(2019, 12, 25))
                .setSkills(Set.of(3L, 2L));
    }

    private static ScheduleDTO createScheduleDTO(List<UUID> petIds, List<UUID> employeeIds,
            LocalDate date, Set<Long> activities) {
        return new ScheduleDTO()
                .setPetIds(petIds)
                .setEmployeeIds(employeeIds)
                .setDate(date)
                .setActivities(activities);
    }

    private ScheduleDTO populateSchedule(int numEmployees, int numPets, LocalDate date,
            Set<Long> activities) {
        List<UUID> employeeIds = IntStream.range(0, numEmployees)
                                          .mapToObj(i -> createEmployeeDTO())
                                          .map(e -> {
                                              e.setSkills(activities);
                                              e.setDaysAvailable(Set.of(date.getDayOfWeek()));
                                              return userController.saveEmployee(e)
                                                                   .getId();
                                          })
                                          .toList();
        CustomerDTO cust = userController.saveCustomer(createCustomerDTO());
        List<UUID> petIds = IntStream.range(0, numPets)
                                     .mapToObj(i -> createPetDTO())
                                     .map(p -> {
                                         p.setOwnerId(cust.getId());
                                         return petController.savePet(p)
                                                             .getId();
                                     })
                                     .toList();
        return scheduleController.createSchedule(
                createScheduleDTO(petIds, employeeIds, date, activities));
    }

    private static void compareSchedules(ScheduleDTO expectedSchedule, ScheduleDTO actualSchedule) {
        assertThat(actualSchedule.getPetIds()).isEqualTo(expectedSchedule.getPetIds());
        assertThat(actualSchedule.getActivities()).isEqualTo(expectedSchedule.getActivities());
        assertThat(actualSchedule.getEmployeeIds()).isEqualTo(expectedSchedule.getEmployeeIds());
        assertThat(actualSchedule.getDate()).isEqualTo(expectedSchedule.getDate());
    }
}
