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


    //public static final String IP = "http://192.168.100.6/";
    public static final String IP = "http://192.168.0.115/";


    public static final String LOGIN_URL = IP + "brawijayaserv/user_controller/login/format/json";
    public static final String LISTRICEFOODS_URL = IP + "brawijayaserv/jobs_controller/get_all_rice_foods/format/json";
    public static final String ADD_CART_URL = IP + "brawijayaserv/jobs_controller/add_cart/format/json";
    public static final String GET_CART_LIST_URL = IP + "brawijayaserv/jobs_controller/get_cart_list/format/json";


    //public static final String LISTJOBS_URL = IP + "kurirsmiserver/jobs_controller/get_courier_jobs/format/json";
}
