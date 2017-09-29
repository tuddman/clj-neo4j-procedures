#!/usr/bin/env node

const exec = require('child_process').exec;

const NEO4J_HOME = '<PATH_TO_NEO4J_HOME>/neo4j-enterprise-3.1.3-SNAPSHOT';

const PLUGIN_NAME = 'clj-neo4j-procedures-0.1.0-SNAPSHOT-standalone.jar';

console.log("~~~ cleaning ~~~");

exec('lein clean', (error, stdout, stderr) => {
    if (error) {
        console.error(`exec error: ${error}`);
        return;
    }
    if (stderr) { console.log(`stderr: ${stderr}`); }
    if (stdout) { console.log(`stdout: ${stdout}`); }
    console.log(`done.`);

    console.log("~~~ building ~~~");

    exec('lein uberjar', (error, stdout, stderr) => {
        if (error) {
            console.error(`exec error: ${error}`);
            return;
        }
        
        if (stderr) { console.log(`stderr: ${stderr}`); }
        if (stdout) { console.log(`stdout: ${stdout}`); }
        
        console.log(`done.`);


        console.log("~~~ stopping neo4j ~~~");

        exec(NEO4J_HOME + "/bin/neo4j stop", (error, stdout, stderr) => {
            if (error) {
                console.error(`exec error: ${error}`);
                return;
            }
            
            if (stderr) { console.log(`stderr: ${stderr}`); }
            if (stdout) { console.log(`stdout: ${stdout}`); }
           
            console.log(`done.`);


            console.log("~~~ removing ~~~");

            exec("rm  " + NEO4J_HOME + "/plugins/" + PLUGIN_NAME, (error, stdout, stderr) => {
                if (error) {
                    console.error(`exec error: ${error}`);
                    return;
                }
            
                if (stderr) { console.log(`stderr: ${stderr}`); }
                if (stdout) { console.log(`stdout: ${stdout}`); }
            
                console.log(`done.`);


                console.log("~~~ copying ~~~");

                exec("cp target/" + PLUGIN_NAME + " " + NEO4J_HOME + "/plugins/", (error, stdout, stderr) => {
                    if (error) {
                        console.error(`exec error: ${error}`);
                        return;
                    }

                    if (stderr) { console.log(`stderr: ${stderr}`); }
                    if (stdout) { console.log(`stdout: ${stdout}`); }
                
                    console.log(`done.`);


                    console.log("~~~ starting neo4j ~~~");

                    exec(NEO4J_HOME + "/bin/neo4j start", (error, stdout, stderr) => {
                        if (error) {
                            console.error(`exec error: ${error}`);
                            return;
                        }

                        if (stderr) { console.log(`stderr: ${stderr}`); }
                        if (stdout) { console.log(`stdout: ${stdout}`); }
                    
                        console.log(`done.`);

                    });

                });

            });

        });

    });

});

