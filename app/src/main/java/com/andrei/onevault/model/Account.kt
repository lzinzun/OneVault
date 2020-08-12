package com.andrei.onevault.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Account (

    @PrimaryKey
    var id:String?=null,
    var title:String?=null,
    var desc:String?=null

) : RealmObject()