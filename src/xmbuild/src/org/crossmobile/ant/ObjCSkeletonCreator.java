/* Copyright (c) 2011 by crossmobile.org
 *
 * CrossMobile is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 2.
 *
 * CrossMobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CrossMobile; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package org.crossmobile.ant;

import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.crossmobile.source.ctype.CLibrary;
import org.crossmobile.source.out.JavaOut;

public class ObjCSkeletonCreator extends Task {

    private File sdkpath;
    private File output;
    private boolean debug;

    public void setSdkpath(File sdkpath) {
        this.sdkpath = sdkpath;
    }

    public void setOutput(File output) {
        this.output = output;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    @Override
    public void execute() throws BuildException {
        if (sdkpath == null)
            throw new BuildException("Parameter sdkpath should be defined.");
        if (output == null)
            throw new BuildException("Parameter output should be defined.");

        if (!sdkpath.isDirectory())
            throw new BuildException("Not valid sdkpath directory: " + sdkpath);
        if (!sdkpath.getName().equals("Frameworks"))
            throw new BuildException("SDK directory \"" + sdkpath.getPath() + "\" should be named \"Frameworks\", e.g. it should be something like /Developer/Platforms/iPhoneOS.platform/Developer/SDKs/iPhoneOS4.3.sdk/System/Library/Frameworks");

        output.mkdirs();
        if (!output.isDirectory())
            throw new BuildException("Output directory " + output.getPath() + " should be a directorty.");

        CLibrary library = new CLibrary("org.xmlvm.iphone");
        for (File frameworks : sdkpath.listFiles()) {
            if (debug)
                System.out.println("Parsing " + frameworks.getPath());
            if (new File(frameworks + File.separator + "Headers").isDirectory())
                for (File f : new File(frameworks, "Headers").listFiles())
                    if (f.getPath().toLowerCase().endsWith(".h"))
                        library.addFile(f.getPath());
        }
        if (debug)
            System.out.println("Finalize");
        library.finalizeLibrary();

        JavaOut out = new JavaOut(output.getPath());
        out.generate(library);
        out.report();
    }
}