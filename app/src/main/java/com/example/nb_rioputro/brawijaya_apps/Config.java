package com.example.nb_rioputro.brawijaya_apps;

/**
 * Created by nb-rioputro on 11/2/2017.
 */

public class Config {
    public static final String SHARED_PREF_NAME = "sprumahsakit";

    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
    public static final String USERNAME_SHARED_PREF = "username";
    public static final String NAME_SHARED_PREF = "name";
    public static final String ROLE_SHARED_PREF = "role";
    public static final String ID_SHARED_PREF = "id";
    public static final String ROOM_SHARED_PREF = "room";
    public static final String REGID_SHARED_PREF = "regid";
    public static final String MRN_SHARED_PREF = "mrn";




    //public static final String IP = "http://192.168.100.95/";
    //public static final String IP = "http://192.168.100.8/";
    //public static final String IP = "http://192.168.100.6/";
    //public static final String IP = "http://192.168.100.5/";
    public static final String IP = "http://192.168.0.115/";
    //public static final String IP = "http://11.22.33.114/";

    //public static final String IP = "http://192.168.1.205/";


    //public static final String IP = "http://11.22.33.9/";
    //public static final String IP = "http://192.168.1.2/";


    //public static final String IP = "http://192.168.43.59/";


    public static final String LOGIN_URL = IP + "brawijayaserv/user_controller/login/format/json";
    public static final String LISTRICEFOODS_URL = IP + "brawijayaserv/jobs_controller/get_all_rice_foods/format/json";
    public static final String ADD_CART_URL = IP + "brawijayaserv/jobs_controller/add_cart/format/json";
    public static final String GET_CART_LIST_URL = IP + "brawijayaserv/jobs_controller/get_cart_list/format/json";
    public static final String UPDATE_ORDER_QTY = IP + "brawijayaserv/jobs_controller/update_order_qty/format/json";
    public static final String REMOVE_ORDER = IP + "brawijayaserv/jobs_controller/remove_order/format/json" ;
    public static final String CHECKOUT = IP + "brawijayaserv/jobs_controller/checkout/format/json" ;
    public static final String GET_CONTACTS_URL = IP + "brawijayaserv/jobs_controller/get_contacts/format/json" ;
    public static final String SEND_SURVEY_URL = IP + "brawijayaserv/jobs_controller/send_survey/format/json" ;
    public static final String GET_SURVEY_URL = IP + "brawijayaserv/jobs_controller/get_survey/format/json" ;
    public static final String GET_CONTACTSPATIENT_URL = IP + "brawijayaserv/jobs_controller/get_contacts_patient/format/json" ;
    public static final String GET_FOOD_URL = IP + "brawijayaserv/jobs_controller/get_food/format/json" ;
    public static final String INSERT_PATIENT_URL = IP + "brawijayaserv/user_controller/insert_patient/format/json" ;
    public static final String GET_PHARMACY_URL = IP + "brawijayaserv/jobs_controller/get_pharmacy/format/json" ;
    public static final String ADD_CARTDETAIL_URL = IP + "brawijayaserv/jobs_controller/add_cart_detail/format/json" ;



}
