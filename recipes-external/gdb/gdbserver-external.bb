SUMMARY = "gdb - GNU debugger"
HOMEPAGE = "http://www.gnu.org/software/gdb/"
SECTION = "devel"
PV := "${@external_run(d, 'gdb', '-v').splitlines()[0].split()[-1]}"

inherit external-toolchain

def get_gdb_license(d):
    output = external_run(d, 'gdb', '-v')
    if output != 'UNKNOWN':
        for line in output.splitlines():
            if line.startswith('License '):
                lic = line.split(':', 1)[0]
                return lic.replace('License ', '')
    else:
        return output

LICENSE := "${@get_gdb_license(d)}"
LICENSE[vardepvalue] = "${LICENSE}"

FILES_${PN} = "\
    ${bindir}/gdbserver \
    ${datadir}/gdb/guile \
    ${datadir}/gdb/python/gdb \
    ${datadir}/gdb/syscalls \
    ${datadir}/gdb/system-gdbinit \
    ${libdir}/libinproctrace.so \
"
INSANE_SKIP_${PN} += "dev-so"
FILES_${PN}-doc = "${mandir}/man1/gdbserver.1"
