package com.sy.giteaapi;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;



/**
 * Simple snippet which shows how to commit a file
 *
 * @author dominik.stadler at gmx.at
 */
public class CommitFile {

    public static void main(String[] args) throws IOException, GitAPIException {
        final File localPath;
        // prepare a new test-repository
        try (Repository repository = CookbookHelper.createNewRepository()) {
            localPath = repository.getWorkTree();

            try (Git git = new Git(repository)) {
                // create the file
                File myFile = new File(repository.getDirectory().getParent(), "testfile");
                if(!myFile.createNewFile()) {
                    throw new IOException("Could not create file " + myFile);
                }

                // run the add
                git.add()
                        .addFilepattern("testfile")
                        .call();

                // and then commit the changes
                git.commit()
                        .setMessage("Added testfile")
                        .call();

                System.out.println("Committed file " + myFile + " to repository at " + repository.getDirectory());
            }
        }

        // clean up here to not keep using more and more disk-space for these samples
        FileUtils.deleteDirectory(localPath);
    }
}