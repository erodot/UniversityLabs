//
//  Database.swift
//  tdbms
//
//  Created by Ted Romanus on 10/24/17.
//  Copyright © 2017 Ted Romanus. All rights reserved.
//

import Foundation

class Database{
    
    //MARK: Properties
    
    var name: String
    var tables: [Table]?
    
    //MARK: Initialization
    
    init(withName name:String, andTables tables:[Table]?){
        self.name = name
        self.tables = tables
    }
    
    convenience init(withName name:String){
        self.init(withName: name, andTables: nil)
    }
}
