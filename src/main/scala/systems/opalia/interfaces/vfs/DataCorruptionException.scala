package systems.opalia.interfaces.vfs

import java.io.IOException


class DataCorruptionException(message: String)
  extends IOException(message)
