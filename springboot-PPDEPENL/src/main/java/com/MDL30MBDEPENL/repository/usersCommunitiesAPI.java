//package com.MDL30MBDEPENL.repository;
//
//import com.MDL30MBDEPENL.model.*;
//import org.springframework.stereotype.Repository;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Repository
//public class usersCommunitiesAPI {
//    // Listings
////    private final List<Users> usersList = new ArrayList<>();
////    private final List<Communities> communitiesList = new ArrayList<>();
////    private final List<Transactions> transactionsList = new ArrayList<>();
//
//    public usersCommunitiesAPI(){
//
//    }
//
//    //Posts
////    @PostConstruct
////    private void initUser(){
////        Users u = new Users(null,
////                "test",
////                "test@test.com",
////                Status.ACTIVE,
////                Type.B2C,
////                LocalDateTime.now(),
////                null,
////                "")
////                ;
////        usersList.add(u);
////    }
////    @PostConstruct
////    private void initCom(){
////        Communities c = new Communities(null,
////                "test",
////                "test",
////                null,
////                "test_ownerUsername",
////                Status.ACTIVE,
////                LocalDateTime.now(),
////                null,
////                "",
////                null)
////                ;
////        communitiesList.add(c);
////    }
////    @PostConstruct
////    private void initTransaction(){
////        Transactions t = new Transactions(null,
////                null,
////                null,
////                null,
////                null,
////                LocalDateTime.now(),
////                "")
////                ;
////        transactionsList.add(t);
////    }
//
//    //methods: User - Community - Transactions
//
//    //find all
//    public List<UsersRepository> usersFindAll(){
//        return usersList;
//    }
//    public List<Communities> communitiesFindAll(){
//        return communitiesList;
//    }
//    public List<Transactions> transactionsFindAll(){
//        return transactionsList;
//    }
//
//    //find by Id
//    public Optional<UsersRepository> usersFindById(Long id){
//        return usersList.stream().filter(u -> u.id().equals(id)).findFirst();
//    }
//    public Optional<Communities> communitiesFindById(Long id){
//        return communitiesList.stream().filter(c -> c.id().equals(id)).findFirst();
//    }
//    public Optional<Transactions> transactionsFindById(Long id){
//        return transactionsList.stream().filter(t -> t.id().equals(id)).findFirst();
//    }
//
//    //savingData
//
//    public void userSave(UsersRepository users){
//        usersList.removeIf(u->u.id().equals(users.id()));
//        usersList.add(users);
//    }
//    public void communitySave(Communities communities){
//        communitiesList.removeIf(c->c.id().equals(communities.id()));
//        communitiesList.add(communities);
//    }
//    public void transactionSave(Transactions transactions){
//        transactionsList.removeIf(t->t.id().equals(transactions.id()));
//        transactionsList.add(transactions);
//    }
//
//    //exists by id
//
//    public boolean userExistsById(Long id){
//        return usersList.stream().filter(u -> u.id().equals(id)).count() == 1;
//    }
//    public boolean communityExistsById(Long id){
//        return communitiesList.stream().filter(c -> c.id().equals(id)).count() == 1;
//    }
//
//    public boolean transactionExistsById(Long id){
//        return transactionsList.stream().filter(c -> c.id().equals(id)).count() == 1;
//    }
//    //delete by id
//    public void userDelete(Long id){
//        usersList.removeIf(u->u.id().equals(id));
//
//    }
//    public void communityDelete(Long id){
//        communitiesList.removeIf(c->c.id().equals(id));
//
//    }
//    public void transactionDelete(Long id){
//        transactionsList.removeIf(t->t.id().equals(id));
//
//    }
//
//    public void addUserToCommunity(Long userId, Long communityId) {
//        Optional<Communities> community = communitiesList.stream().filter(c -> c.id().equals(communityId)).findFirst();
//        Optional<UsersRepository> userToAdd = usersList.stream().filter(u -> u.id().equals(userId)).findFirst();
//
//        if (community.isPresent() && userToAdd.isPresent()) {
//            community.get().getUsers().add(userToAdd.get());
//        }
//    }
//
//    public void deleteUserFromCommunity(Long userId, Long communityId) {
//        Optional<Communities> community = communitiesList.stream().filter(c -> c.id().equals(communityId)).findFirst();
//        Optional<UsersRepository> userToRemove = usersList.stream().filter(u -> u.id().equals(userId)).findFirst();
//
//        if (community.isPresent() && userToRemove.isPresent()) {
//            community.get().getUsers().remove(userToRemove.get());
//        }
//    }
//
//    public void deleteTransactionById(Long transactionId) {
//        transactionsList.removeIf(t -> t.id().equals(transactionId));
//    }
//
//    public List<Transactions> listTransactionsByUser(Long userId) {
//        return transactionsList.stream()
//                .filter(t -> t.user1Id().equals(userId) || t.user2Id().equals(userId))
//                .collect(Collectors.toList());
//    }
//    public void deleteTransactionsByUser(Long userId) {
//        transactionsList.removeIf(t -> t.user1Id().equals(userId) || t.user2Id().equals(userId));
//    }
//    public List<UsersRepository> listUsersByCommunityId(Long communityId) {
//        Optional<Communities> community = communitiesList.stream().filter(c -> c.id().equals(communityId)).findFirst();
//
//        if (community.isPresent()) {
//            return community.get().getUsers();
//        } else {
//            return Collections.emptyList();
//        }
//    }
//    public List<UsersRepository> listUsersByCommunityName(String communityName) {
//        Optional<Communities> community = communitiesList.stream().filter(c -> c.name().equals(communityName)).findFirst();
//
//        if (community.isPresent()) {
//            return community.get().getUsers();
//        } else {
//            return Collections.emptyList();
//        }
//    }
//}
