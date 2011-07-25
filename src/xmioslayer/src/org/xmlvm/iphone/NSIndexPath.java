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
 * along with Jubler; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 */
package org.xmlvm.iphone;

public class NSIndexPath extends NSObject {

    private int section;
    private int row;

    public NSIndexPath() {
    }

    NSIndexPath(int section, int row) {
        this.section = section;
        this.row = row;
    }

    public static NSIndexPath indexPathForRow(int row, int section) {
        NSIndexPath path = new NSIndexPath();
        path.row = row;
        path.section = section;
        return path;
    }

    public int getSection() {
        return section;
    }

    public int getRow() {
        return row;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public void setRow(int row) {
        this.row = row;
    }
}