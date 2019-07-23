package br.com.bittencourt.boni.lucas.blueshoes.domain;

public class CreditCard {
    /*
     * number contém somente o último conjunto de
     * números do cartão (que são 4 ou 3 números).
     * */
    private String number;
    private String enterprise;
    private String ownerFullName;
    private String ownerRegNumber = "";
    private Integer expiryMonth = 0;
    private Integer expiryYear = 0;
    private String securityNumber;

    public CreditCard(String number, String enterprise, String ownerFullName) {
        this.number = number;
        this.enterprise = enterprise;
        this.ownerFullName = ownerFullName;
    }

    public String getNumberAsHidden() {
        return String.format("**** **** **** %s", number);
    }

    public String getOwnerFullNameAsHidden() {
        String[] nameList = ownerFullName.split(" ");

        String firstName = nameList[0].substring(0, 2);
        String lastName = nameList[nameList.length - 1];

        return String.format("%s... %s", firstName, lastName);
    }

    public String getEnterprise() {
        return enterprise;
    }
}
