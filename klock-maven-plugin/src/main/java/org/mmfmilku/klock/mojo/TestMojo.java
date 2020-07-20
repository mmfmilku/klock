package org.mmfmilku.klock.mojo;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.util.List;

@Mojo(name = "test")
public class TestMojo
        extends AbstractMojo {
    /**
     * Location of the file.
     *
     * @parameter expression="${project.build.directory}"
     * @required
     */
    private File outputDirectory;

    @Parameter(property = "project.build.finalName")
    private String finalName;

    @Parameter(property = "project.packaging")
    private String packaging;

    @Parameter(property = "project.build.directory")
    private String buildDir;

    @Parameter(property = "project.build.outputDirectory")
    private String outputDir;

    @Parameter(property = "file.separator")
    private String fileSeparator;

    @Parameter
    private List<String> encryptDirs;

    public void execute()
            throws MojoExecutionException {
        System.out.println("finalName" + finalName);
        System.out.println("packaging" + packaging);
        System.out.println("buildDir" + buildDir);
        System.out.println("outputDir" + outputDir);
        System.out.println("fileSeparator" + fileSeparator);
        System.out.println("encryptDirs" + encryptDirs.toString());
    }
}
