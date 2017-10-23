//
//  DatabaseChooserViewController.swift
//  tdbms
//
//  Created by Ted Romanus on 10/24/17.
//  Copyright © 2017 Ted Romanus. All rights reserved.
//

import UIKit

class DatabaseChooserViewController: UITableViewController {
    
    // MARK: - Properties
    var databases = [Database]()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        loadSampleDatabases()
        
        // Use the edit button item provided by the table view controller.
        navigationItem.leftBarButtonItem = editButtonItem
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    // MARK: - UITableView methods
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return databases.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        // create a new cell if needed or reuse an old one
        let cell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath)
        
        // set the text from the data model
        cell.textLabel?.text = databases[indexPath.row].name
        
        return cell
    }
    
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
//            meals.remove(at: indexPath.row)
//            saveMeals()
//            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }
    }

    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    
    // MARK: - Private methods

    private func loadSampleDatabases(){
        let database1 = Database(withName: "Database1")
        let database2 = Database(withName: "Database2")
        let database3 = Database(withName: "Database3")
        
        databases += [database1, database2, database3]
    }
}
