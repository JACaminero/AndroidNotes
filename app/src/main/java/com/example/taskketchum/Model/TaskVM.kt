package com.example.taskketchum.Model

import java.io.Serializable

class TaskVM(id: Int, title: String?, description: String?, date: String?) : Serializable {
    var id: Int = 0
    var title: String? = ""
    var description: String? = ""
    var date: String? = ""

    init  {
        this.id = id
        this.title = title
        this.description = description
        this.date = date
    }
}