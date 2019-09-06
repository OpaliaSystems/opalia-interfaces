package systems.opalia.interfaces.vfs


trait VfsService {

  def getFileSystem(name: String): FileSystem
}
