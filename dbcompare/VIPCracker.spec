# -*- mode: python -*-

block_cipher = None


a = Analysis(['VIPCracker.py'],
             pathex=['conn', 'util', 'dbcompare', 'D:\\Code\\lifealien\\dbcompare'],
             binaries=[],
             datas=[],
             hiddenimports=['ConnFactory.py', 'ExeE.py', 'Factory.py', 'FileUtil.py', 'parse_excel.py'],
             hookspath=[],
             runtime_hooks=[],
             excludes=[],
             win_no_prefer_redirects=False,
             win_private_assemblies=False,
             cipher=block_cipher,
             noarchive=False)
pyz = PYZ(a.pure, a.zipped_data,
             cipher=block_cipher)
exe = EXE(pyz,
          a.scripts,
          a.binaries,
          a.zipfiles,
          a.datas,
          [],
          name='VIPCracker',
          debug=False,
          bootloader_ignore_signals=False,
          strip=False,
          upx=True,
          runtime_tmpdir=None,
          console=True )
